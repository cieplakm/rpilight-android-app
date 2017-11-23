package com.mmc.mateusz.rpilight;

import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.Socket;


import java.io.DataOutputStream;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableCreate;
import io.reactivex.schedulers.Schedulers;

//http://androidsrc.net/android-client-server-using-sockets-client-implementation/

public class Client {

    int PORT=8880;

    private String AddressIP;

    private Boolean isLightOn;

    private Socket clientSocket= null;
    private DataOutputStream outObject;
    private DataInputStream inObject;
    private LampListener lampListener;


    public Client(String aIPaddres) {
        AddressIP = aIPaddres;
    }


    protected Observable observable(){
       return ObservableCreate.just("GOGOGOG!")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
               .map(new Function() {
                   @Override
                   public Object apply(@NonNull Object o) throws Exception {
                       openSocket();
                       openStreams();
                       return new Object();
                   }
               });
    }

    public void getLampData() {
new Thread(new Runnable() {
    @Override
    public void run() {
        isLightOn = false;
        openSocket();
        openStreams();
        getData();
    }
}).start();


    }

    private void informListener() {

        if (isLightOn){
            lampListener.onLampOn();
        }else {
            lampListener.onLampOff();
        }

    }

    public void setLampData() {

        observable().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull String s) {
                sendData();
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
                closeSocket();
                informListener();

            }
        });

    }

    protected void openSocket() {
        try {

            clientSocket = new Socket(AddressIP, PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void openStreams(){
        try {

            inObject = new DataInputStream(clientSocket.getInputStream());
            outObject = new DataOutputStream(clientSocket.getOutputStream());

        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void closeSocket(){
        try {
            inObject.close();
            outObject.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void getData() {
        try {
            isLightOn = inObject.readBoolean();
            Log.d("LAMP", "Dane: " + isLightOn);

        }  catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void sendData(){
        try {
            outObject.writeInt(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setLampListener(LampListener onLampListener) {
        this.lampListener = onLampListener;
    }
}

package com.mmc.mateusz.rpilight;

import android.graphics.Color;
import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.UnknownHostException;



import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
//http://androidsrc.net/android-client-server-using-sockets-client-implementation/
public class Client extends AsyncTask<Void, Void, Void> {

    private String AddressIP;
    private int Port;
    ImageView kontrolka;
    Boolean s;
    public ConnectionInterface interfaceField=null;
    private Socket clientSocket= null;
    private DataOutputStream outObject;
    private DataInputStream inObject;
    private int option;

    public Client(String aIPaddres, int aPort, int oprion) {
        AddressIP = aIPaddres;
        Port = aPort;

        this.option=oprion;


    }
    public void openSocket() throws IOException {
        clientSocket = new Socket(AddressIP, Port);
    }

    public void openStreams(){
        try {
            //open input stream
            inObject = new DataInputStream(
                    clientSocket.getInputStream());
            //open output stream
            outObject = new DataOutputStream(
                    clientSocket.getOutputStream());


        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        try {
            openSocket();
            openStreams();
            sendData(option);
            getData();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


        return null;

    }
    public void getData() {
        try {
            s = (Boolean)inObject.readBoolean();

        }  catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void sendData(int option){
        try {
            outObject.writeInt(option);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    interface ConnectionInterface{
        void answerFromServer(Boolean boo);
    }

    protected void onPostExecute(Void aVoid) {
        super.onPreExecute();
        if (s!= null) {
            interfaceField.answerFromServer(s);
        }

    }


}

package com.mmc.mateusz.rpilight;


import com.mmc.rpilight.OnResponseListener;
import com.mmc.rpilight.RPiLight;
import com.mmc.rpilight.client.Client;
import com.mmc.rpilight.server.Request;
import com.mmc.rpilight.server.Response;

public class MainPresenter implements Contract.Presenter {
    public static final String IP = "192.168.1.10";

    private Contract.View view;

    Client client;

    @Override
    public void onCreate(Contract.View view1) {

        this.view = view1;
        client = RPiLight.clientInstance("192.168.1.15");



        client.setOnReciveListener(new OnResponseListener() {
            @Override
            public void onResponse(Response response) {
                System.out.println("Server responsed! Lamp is ON: " + response.isLampOn());
                view.setBulpOn(response.isLampOn());
            }
        });

        requet4Info();

    }

    @Override
    public void onTrigerClick() {
        requet4Info();
    }

    @Override
    public void onTrigerOnClick() {
        setLampOn(true);
    }

    @Override
    public void onTrigerOffClick() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                setLampOn(false);

            }
        }).start();
    }

    public void setLampOn(final Boolean on){
        new Thread(new Runnable() {
            @Override
            public void run() {

                client.request(new Request(on)); //request to get info

            }
        }).start();
    }

    public void requet4Info(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                client.request(new Request()); //request to get info

            }
        }).start();
    }
}



package com.mmc.mateusz.rpilight;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mmc.rpilight.OnResponseListener;
import com.mmc.rpilight.RPiLight;
import com.mmc.rpilight.client.Client;
import com.mmc.rpilight.server.Request;
import com.mmc.rpilight.server.Response;

import static com.mmc.mateusz.rpilight.Settings.IP;

public class MainPresenter implements Contract.Presenter,OnResponseListener {
    public String ip;

    private Contract.View view;

    Client client;

    @Override
    public void onCreate(Contract.View view1) {

        this.view = view1;

        setupIP();

        new Thread(new Runnable() {
            @Override
            public void run() {
                client = RPiLight.clientInstance(ip);
                client.setOnReciveListener(MainPresenter.this);
                requet4Info();
            }
        }).start();



    }

    private void setupIP() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context) view);
        if (sharedPreferences.contains(IP)){
            ip = sharedPreferences.getString(IP,"0.0.0.0");
        }else {
            view.openSettingActivity();
            view.finish();
        }

    }

    @Override
    public void onInfoStateClick() {
        requet4Info();
    }

    @Override
    public void onLampOnClick() {
        setLampOn(true);
    }

    @Override
    public void onLampOffClick() {
        setLampOn(false);
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

    @Override
    public void onResponse(Response response) {
        view.setBulpOn(response.isLampOn());
    }
}



package com.mmc.mateusz.rpilight;


import android.widget.Toast;

import com.mmc.rpilight.OnResponseListener;
import com.mmc.rpilight.client.Client;
import com.mmc.rpilight.client.ClientImplementation;
import com.mmc.rpilight.server.Request;
import com.mmc.rpilight.server.Response;

public class MainPresenter implements Contract.Presenter {
    public static final String IP = "192.168.1.5";

    private Contract.View view;

    @Override
    public void onCreate(Contract.View view) {

        this.view = view;
    }

    @Override
    public void onTrigerClick() {



        new Thread(new Runnable() {
            @Override
            public void run() {

                final Client client = new ClientImplementation(IP);

                client.setOnReciveListener(new OnResponseListener() {
                    @Override
                    public void onRecive(Response response) {
                        view.showToast(response.getMessage());
                    }
                });

                final Request request = new Request();
                request.cmd = "Hello!";


                client.request(request);
            }
        }).start();

    }
}



package com.mmc.mateusz.rpilight;

import android.util.Log;

/**
 * Created by Moni on 2017-11-22.
 */

public class MainPresenter implements Contract.Presenter, LampListener {
    public static final String IP  = "192.168.1.7";

    private Contract.View view;

    @Override
    public void onCreate(Contract.View view) {

        this.view = view;
    }

    @Override
    public void onTrigerClick() {

        Client client = new Client(IP);
        client.setLampListener(this);
        client.getLampData();



    }
    @Override
    public void onLampOn() {
        Log.d("LAMP0", "I am ON!");
    }

    @Override
    public void onLampOff() {
        Log.d("LAMP0", "I am OFF!");
    }
}


//    @Override
//    public void answerFromServer(Boolean boo) {
//        if (boo == true){
//            btnTriger.setImageResource(R.drawable.bylboff);
//        }else{
//            btnTriger.setImageResource(R.drawable.bylbon);
//        }
//    }
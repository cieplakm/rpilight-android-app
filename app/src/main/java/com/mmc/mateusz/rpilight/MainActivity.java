package com.mmc.mateusz.rpilight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements Client.ConnectionInterface{
    private int ONOF;
    private EditText ip;
    private String IP;
    private int PORT;
    ImageView kontrolka;
    ImageButton triger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        triger = (ImageButton)findViewById(R.id.triger);

        SharedPreferences sharedpreferences = getSharedPreferences("DANE", MODE_PRIVATE);

        if (sharedpreferences.contains("IP")){
            IP=sharedpreferences.getString("IP","");

        }else{
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }

        ImageButton ibtnSettings = (ImageButton)findViewById(R.id.imageButton);

        ibtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                finish();
            }
        });

       PORT=8880;


        triger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Client client = new Client(IP,PORT, 1);
                client.interfaceField=MainActivity.this;
                    client.execute();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Client client = new Client(IP,PORT, 2);
        client.interfaceField=MainActivity.this;
        client.execute();
    }

    @Override
    public void answerFromServer(Boolean boo) {
        if (boo == true){
            triger.setImageResource(R.drawable.bylboff);
        }else{
            triger.setImageResource(R.drawable.bylbon);
        }
    }
}

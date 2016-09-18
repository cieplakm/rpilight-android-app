package com.mmc.mateusz.rpilight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final EditText etIP = (EditText)findViewById(R.id.etIP);
        Button btnOk = (Button) findViewById(R.id.btnOK);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etIP.getText().toString().equals("")){
                    SharedPreferences sharedPreferences = getSharedPreferences("DANE", MODE_PRIVATE);
                    SharedPreferences.Editor sharedEditor = sharedPreferences.edit();

                    sharedEditor.putString("IP", etIP.getText().toString());
                    sharedEditor.commit();

                    Intent intent = new Intent(Settings.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }
}

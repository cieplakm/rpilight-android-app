package com.mmc.mateusz.rpilight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Contract.View{
    ImageButton btnTriger;
    ImageButton btnSettings;
    Contract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setupBtnListeners();

        presenter = new MainPresenter();
        presenter.onCreate(this);

    }

    private void setupBtnListeners() {
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSettingActivity();

            }
        });


        btnTriger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onTrigerClick();
            }
        });


    }

    @Override
    public void openSettingActivity() {

        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);

    }

    private void bindViews() {

        btnTriger = (ImageButton)findViewById(R.id.triger);
        btnSettings = (ImageButton)findViewById(R.id.imageButton);

    }

    @Override
    public void setBulpOn(final Boolean boo) {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (boo){
                    btnTriger.setImageResource(R.drawable.bylbon);
                }else{
                    btnTriger.setImageResource(R.drawable.bylboff);
                }
            }
        });

    }

    @Override
    public void showToast(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


}

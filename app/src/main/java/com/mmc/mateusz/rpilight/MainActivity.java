package com.mmc.mateusz.rpilight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

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



}

package com.mmc.mateusz.rpilight;

/**
 * Created by Moni on 2017-11-22.
 */

public interface Contract {
    public interface Presenter {
        void onCreate(View view);

        void onTrigerClick();
    }

    public interface View {

        void openSettingActivity();

        void setBulp(Boolean boo);

        void showToast(String msg);
    }
}

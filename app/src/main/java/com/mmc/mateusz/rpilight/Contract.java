package com.mmc.mateusz.rpilight;

/**
 * Created by Moni on 2017-11-22.
 */

public interface Contract {
    public interface Presenter {
        void onCreate(View view);

        void onInfoStateClick();

        void onLampOnClick();

        void onLampOffClick();
    }

    public interface View {

        void openSettingActivity();

        void setBulpOn(Boolean boo);

        void showToast(String msg);

        void finish();
    }

}

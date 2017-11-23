package com.mmc.mateusz.rpilight;

/**
 * Created by Moni on 2017-11-22.
 */

public class Contract {
    public interface Presenter {
        void onCreate(View view);

        void onTrigerClick();
    }

    public interface View {

        void openSettingActivity();
    }
}

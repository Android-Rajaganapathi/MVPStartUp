package com.rajaganapathi.mvp.presenter.ipresenter;

import android.content.Intent;
import android.os.Bundle;

public interface IPresenter {

    void onCreate(Bundle bundle);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onActivityForResult(int requestCode, int resultCode, Intent data);

    String stringFromRes(int resId);

    void makeLogout(String s);
}

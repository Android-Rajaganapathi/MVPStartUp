package com.rajaganapathi.mvp;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

public class ProjectApplication extends Application {

    private static ProjectApplication mApplication;
    public Context mContext;

    public static ProjectApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;
        initApplication();
    }

    private void initApplication() {
        mContext = getApplicationContext();
    }

    public ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}

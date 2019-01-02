package com.rajaganapathi.mvp.presenter;

import android.os.Bundle;
import android.os.Handler;

import com.rajaganapathi.mvp.presenter.ipresenter.ISplashPresenter;
import com.rajaganapathi.mvp.view.iview.ISplashView;

public class SplashPresenter extends BasePresenter<ISplashView> implements ISplashPresenter {

    private static final int SPLASH_TIME_OUT = 3000;

    public SplashPresenter(ISplashView iView) {
        super(iView);
        splashTimer();
    }

    private void splashTimer() {
        new Handler().postDelayed(() -> {
            if (CheckInternetConnectivity()) {
                if (checkUserStatus()) iView.goToDashboard(new Bundle());
                else iView.goToAuthentication(new Bundle());
            } else iView.showNetworkAlert();
        }, SplashPresenter.SPLASH_TIME_OUT);
    }

    @Override
    public boolean CheckInternetConnectivity() {
        return true;
        //      return iView.getCodeSnippet().hasNetworkConnection();
    }

    @Override
    public boolean checkUserStatus() {
        return true;
    }
}

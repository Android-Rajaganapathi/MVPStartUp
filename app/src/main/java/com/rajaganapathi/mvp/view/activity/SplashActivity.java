package com.rajaganapathi.mvp.view.activity;

import android.os.Bundle;

import com.rajaganapathi.mvp.R;
import com.rajaganapathi.mvp.presenter.SplashPresenter;
import com.rajaganapathi.mvp.presenter.ipresenter.ISplashPresenter;
import com.rajaganapathi.mvp.view.iview.ISplashView;

public class SplashActivity extends BaseActivity<ISplashPresenter> implements ISplashView {

    @Override
    protected void initViews() {

    }

    @Override
    int attachLayout() {
        return R.layout.activity_splash;
    }

    @Override
    ISplashPresenter initPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    public void goToAuthentication(Bundle mBundle) {
        navigateTo(AuthenticationActivity.class, true, mBundle);
    }

    @Override
    public void goToDashboard(Bundle mBundle) {
        navigateTo(AuthenticationActivity.class, true, mBundle);
    }
}

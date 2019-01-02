package com.rajaganapathi.mvp.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.rajaganapathi.mvp.presenter.ipresenter.IPresenter;
import com.rajaganapathi.mvp.view.iview.IView;

public abstract class BasePresenter<V extends IView> implements IPresenter {

    protected V iView;

    public BasePresenter(V iView) {
        this.iView = iView;
    }

    @Override
    public void onCreate(Bundle bundle) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onActivityForResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public String stringFromRes(int resId) {
        return iView.getActivity().getString(resId);
    }

    @Override
    public void makeLogout(String s) {
        iView.showAlert(s);
        iView.logoutSession();
        iView.hideProgressbar();
    }

}

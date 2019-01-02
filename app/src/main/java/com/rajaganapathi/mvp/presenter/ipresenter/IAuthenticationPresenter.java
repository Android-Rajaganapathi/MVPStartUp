package com.rajaganapathi.mvp.presenter.ipresenter;

public interface IAuthenticationPresenter extends IPresenter {

    void goToLogin();

    void goToRegistration();

    void goToForgotPassword();
}

package com.rajaganapathi.mvp.presenter;

import com.rajaganapathi.mvp.presenter.ipresenter.IAuthenticationPresenter;
import com.rajaganapathi.mvp.view.iview.IAuthenticationView;

public class AuthenticationPresenter extends BasePresenter<IAuthenticationView>
        implements IAuthenticationPresenter {

    public AuthenticationPresenter(IAuthenticationView iView) {
        super(iView);
    }

    @Override
    public void goToLogin() {

    }

    @Override
    public void goToRegistration() {

    }

    @Override
    public void goToForgotPassword() {

    }
}

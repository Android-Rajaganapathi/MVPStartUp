package com.rajaganapathi.mvp.view.iview;

import android.os.Bundle;

public interface IAuthenticationView extends IView {

    void goToDashboard(Bundle mBundle);

    void goToRegistration(Bundle mBundle);

    void goToForgotPassword(Bundle mBundle);
}

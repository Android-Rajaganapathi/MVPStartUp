package com.rajaganapathi.mvp.view.iview;

import android.os.Bundle;

public interface ISplashView extends IView {

    void goToAuthentication(Bundle mBundle);

    void goToDashboard(Bundle mBundle);
}

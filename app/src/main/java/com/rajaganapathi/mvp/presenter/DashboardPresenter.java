package com.rajaganapathi.mvp.presenter;

import android.os.Bundle;

import com.rajaganapathi.mvp.presenter.ipresenter.IDashboardPresenter;
import com.rajaganapathi.mvp.view.iview.IDashboardView;

public class DashboardPresenter extends BasePresenter<IDashboardView>
        implements IDashboardPresenter {

    public DashboardPresenter(IDashboardView iView) {
        super(iView);
        iView.openHomeFragment(new Bundle());
    }
}

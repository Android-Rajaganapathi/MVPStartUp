package com.rajaganapathi.mvp.view.activity;

import android.os.Bundle;

import com.rajaganapathi.mvp.R;
import com.rajaganapathi.mvp.presenter.DashboardPresenter;
import com.rajaganapathi.mvp.presenter.ipresenter.IDashboardPresenter;
import com.rajaganapathi.mvp.view.fragment.HomeFragment;
import com.rajaganapathi.mvp.view.iview.IDashboardView;

public class DashboardActivity extends BaseActivity<IDashboardPresenter>
        implements IDashboardView {

    @Override
    protected void initViews() {
    }

    @Override
    int attachLayout() {
        return R.layout.activity_dashboard;
    }

    @Override
    IDashboardPresenter initPresenter() {
        return new DashboardPresenter(this);
    }

    @Override
    public void openHomeFragment(Bundle bundle) {
        replaceFragment(bundle, R.id.flContainer, new HomeFragment());
    }
}

package com.rajaganapathi.mvp.view.fragment;

import com.rajaganapathi.mvp.R;
import com.rajaganapathi.mvp.presenter.HomePresenter;
import com.rajaganapathi.mvp.presenter.ipresenter.IHomePresenter;
import com.rajaganapathi.mvp.view.iview.IHomeView;

public class HomeFragment extends BaseFragment<IHomePresenter> implements IHomeView {

    @Override
    int attachLayout() {
        return R.layout.fragment_home;
    }

    @Override
    IHomePresenter initializer() {
        return new HomePresenter(this);
    }
}

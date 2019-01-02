package com.rajaganapathi.mvp.presenter;

import com.rajaganapathi.mvp.presenter.ipresenter.IHomePresenter;
import com.rajaganapathi.mvp.view.iview.IHomeView;

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter {

    public HomePresenter(IHomeView iView) {
        super(iView);
    }

}

package com.rajaganapathi.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rajaganapathi.mvp.common.CodeSnippet;
import com.rajaganapathi.mvp.common.PermissionUtils;
import com.rajaganapathi.mvp.common.PreferencesHelper;
import com.rajaganapathi.mvp.presenter.ipresenter.IPresenter;
import com.rajaganapathi.mvp.view.iview.IView;

import java.util.Objects;

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IView {

    protected P iPresenter;

    protected View vRootView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vRootView = LayoutInflater.from(getContext()).inflate(attachLayout(), container, false);
        iPresenter = initializer();
        assert getArguments() != null;
        iPresenter.onCreate(getArguments());
        return vRootView;
    }

    abstract int attachLayout();

    abstract P initializer();

    @Override
    public void showToast(String message) {
        ((IView) Objects.requireNonNull(getActivity())).showToast(message);
    }

    @Override
    public void showAlert(String message) {
        ((IView) Objects.requireNonNull(getActivity())).showAlert(message);
    }

    @Override
    public void showSnackBar(String message) {
        ((IView) Objects.requireNonNull(getActivity())).showSnackBar(message);
    }

    @Override
    public void showSnackBar(@NonNull View view, String message) {
        ((IView) Objects.requireNonNull(getActivity())).showSnackBar(view, message);
    }

    @Override
    public void logoutSession() {
        ((IView) Objects.requireNonNull(getActivity())).logoutSession();
    }

    @Override
    public boolean isNetworkEnabled() {
        return ((IView) Objects.requireNonNull(getActivity())).isNetworkEnabled();
    }

    @Override
    public String stringFromRes(int resId) {
        return ((IView) Objects.requireNonNull(getActivity())).stringFromRes(resId);
    }

    @Override
    public void showProgressbar() {
        ((IView) Objects.requireNonNull(getActivity())).showProgressbar();
    }

    @Override
    public void hideProgressbar() {
        ((IView) Objects.requireNonNull(getActivity())).hideProgressbar();
    }

    @Override
    public void showNetworkAlert() {
        ((IView) Objects.requireNonNull(getActivity())).showNetworkAlert();
    }

    @Override
    public CodeSnippet getCodeSnippet() {
        return ((IView) Objects.requireNonNull(getActivity())).getCodeSnippet();
    }

    @Override
    public PreferencesHelper getPrefHelper() {
        return ((IView) Objects.requireNonNull(getActivity())).getPrefHelper();
    }

    @Override
    public PermissionUtils getPermissionUtil() {
        return ((IView) Objects.requireNonNull(getActivity())).getPermissionUtil();
    }

    @Override
    public void navigateTo(@NonNull Class<?> cls, boolean isFinishActivity, @NonNull Bundle bundle) {
        ((IView) Objects.requireNonNull(getActivity())).navigateTo(cls, isFinishActivity, bundle);
    }
}
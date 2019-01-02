package com.rajaganapathi.mvp.view.iview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.rajaganapathi.mvp.common.CodeSnippet;
import com.rajaganapathi.mvp.common.PermissionUtils;
import com.rajaganapathi.mvp.common.PreferencesHelper;

public interface IView {

    void showToast(String message);

    void showAlert(String message);

    void showSnackBar(String message);

    void showSnackBar(@NonNull View view, String message);

    void logoutSession();

    boolean isNetworkEnabled();

    String stringFromRes(int resId);

    void showProgressbar();

    void hideProgressbar();

    void showNetworkAlert();

    FragmentActivity getActivity();

    CodeSnippet getCodeSnippet();

    PermissionUtils getPermissionUtil();

    PreferencesHelper getPrefHelper();

    void navigateTo(@NonNull Class<?> cls, boolean isFinishActivity, @NonNull Bundle bundle);
}
package com.rajaganapathi.mvp.view.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.Toast;

import com.rajaganapathi.mvp.R;
import com.rajaganapathi.mvp.presenter.AuthenticationPresenter;
import com.rajaganapathi.mvp.presenter.ipresenter.IAuthenticationPresenter;
import com.rajaganapathi.mvp.view.iview.IAuthenticationView;

public class AuthenticationActivity
        extends BaseActivity<IAuthenticationPresenter>
        implements IAuthenticationView {

    private TextInputLayout tilMobile, tilPassword;
    private TextInputEditText ietMobile, ietPassword;

    @Override
    protected void initViews() {
        tilMobile = findViewById(R.id.tilMobileNumber);
        tilPassword = findViewById(R.id.tilPassword);
        ietMobile = findViewById(R.id.etMobileNumber);
        ietPassword = findViewById(R.id.etPassword);
        TextView tvRegister = findViewById(R.id.tvRegister);
        TextView tvForgotPass = findViewById(R.id.tvForgotPass);

        getPrefHelper().setString("mobile", "9988776655");
        getPrefHelper().setString("password", "aA1!1234");

        ietMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!getCodeSnippet().isValidPhoneNumber(ietMobile.getText().toString()))
                    showTextInputLayoutError(tilMobile, ietMobile, getString(R.string.enter_mobile));
                else if (ietMobile.getText().length() == 10
                        && getPrefHelper().getString("mobile").equals(ietMobile.getText().toString())) {
                    ietPassword.requestFocus();
                    tilMobile.setError(null);
                    tilMobile.setErrorEnabled(false);
                } else if (ietMobile.getText().length() == 10) {
                    ietPassword.requestFocus();
                    tilMobile.setError(null);
                    tilMobile.setErrorEnabled(false);
                }
            }
        });

        ietPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!getCodeSnippet().isValidPassword(ietPassword.getText().toString()))
                    showTextInputLayoutError(tilPassword, ietPassword, getString(R.string.enter_password));
                else if (TextUtils.equals(ietPassword.getText().toString(), getPrefHelper().getString("password"))) {
                    goToDashboard(new Bundle());
                    tilPassword.setError(null);
                    tilPassword.setErrorEnabled(false);
                } else {
                    Toast.makeText(AuthenticationActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    tilPassword.setError(null);
                    tilPassword.setErrorEnabled(false);
                }
            }
        });

        tvRegister.setOnClickListener(v -> goToRegistration(new Bundle()));

        tvForgotPass.setOnClickListener(v -> goToForgotPassword(new Bundle()));
    }

    @Override
    int attachLayout() {
        return R.layout.activity_authentication;
    }

    @Override
    IAuthenticationPresenter initPresenter() {
        return new AuthenticationPresenter(this);
    }

    @Override
    public void goToDashboard(Bundle mBundle) {
        navigateTo(DashboardActivity.class, true, mBundle);
    }

    @Override
    public void goToRegistration(Bundle mBundle) {
        showToast("Goes to Registration Screen...");
    }

    @Override
    public void goToForgotPassword(Bundle mBundle) {
        showToast("Goes to Forgot Password Screen...");
    }
}

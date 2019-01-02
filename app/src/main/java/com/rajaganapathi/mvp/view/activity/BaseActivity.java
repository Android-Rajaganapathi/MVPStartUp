package com.rajaganapathi.mvp.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.rajaganapathi.mvp.R;
import com.rajaganapathi.mvp.common.CodeSnippet;
import com.rajaganapathi.mvp.common.PermissionUtils;
import com.rajaganapathi.mvp.common.PreferencesHelper;
import com.rajaganapathi.mvp.presenter.ipresenter.IPresenter;
import com.rajaganapathi.mvp.view.iview.IView;
import com.rajaganapathi.mvp.view.widget.CustomProgressbar;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView {

    protected String TAG = getClass().getSimpleName();
    protected View mParentView;
    protected CodeSnippet mCodeSnippet;
    protected PreferencesHelper mPrefHelper;
    protected CustomProgressbar mCustomProgressbar;
    protected P iPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(attachLayout());
        iPresenter = initPresenter();
        iPresenter.onCreate(getIntent().getExtras());

        initViews();
    }

    protected abstract void initViews();

    abstract int attachLayout();

    abstract P initPresenter();

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        mParentView = getWindow().getDecorView().findViewById(android.R.id.content);
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();
        assert iPresenter != null;
        iPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        assert iPresenter != null;
        iPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        assert iPresenter != null;
        iPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        assert iPresenter != null;
        iPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        assert iPresenter != null;
        iPresenter.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        iPresenter.onActivityForResult(requestCode, resultCode, data);
    }

    private CustomProgressbar getProgressBar() {
        if (mCustomProgressbar == null) mCustomProgressbar = new CustomProgressbar(this);
        return mCustomProgressbar;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlert(String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(getText(android.R.string.ok), (dialog, which) -> dialog.cancel());
        alertDialog.show();
    }

    @Override
    public void showProgressbar() {
        try {
            getProgressBar().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideProgressbar() {
        runOnUiThread(() -> {
            try {
                getProgressBar().hideProgress();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public FragmentActivity getActivity() {
        return this;
    }

    @Override
    public void showSnackBar(String message) {
        if (mParentView != null) {
            Snackbar snackbar = Snackbar.make(mParentView, message, Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.WHITE);
            snackbar.show();
        }
    }

    @Override
    public void showSnackBar(@NonNull View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    @Override
    public void showNetworkAlert() {
        showToast(stringFromRes(R.string.no_internet_available));
    }

    @Override
    public CodeSnippet getCodeSnippet() {
        if (mCodeSnippet == null) {
            mCodeSnippet = new CodeSnippet(getActivity());
            return mCodeSnippet;
        }
        return mCodeSnippet;
    }

    @Override
    public PreferencesHelper getPrefHelper() {
        if (mPrefHelper == null) {
            mPrefHelper = new PreferencesHelper(getActivity());
            return mPrefHelper;
        }
        return mPrefHelper;
    }

    @Override
    public PermissionUtils getPermissionUtil() {
        return new PermissionUtils();
    }

    @Override
    public boolean isNetworkEnabled() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null) return info.getState() == NetworkInfo.State.CONNECTED;
        }
        return false;
    }

    @Override
    public String stringFromRes(int resId) {
        return getString(resId);
    }

    @Override
    public void logoutSession() {
        iPresenter.makeLogout(stringFromRes(R.string.session_expired));
    }

    public void replaceFragment(Bundle bundle,
                                @IdRes int containerViewId,
                                @NonNull Fragment fragment) {
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(containerViewId, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    public void showTextInputLayoutError(TextInputLayout textInputLayout, TextInputEditText editText, String errorMsg) {
        textInputLayout.setError(errorMsg);
        requestFocus(editText);
    }

    private void requestFocus(View view) {
        if (view.requestFocus())
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @Override
    public void navigateTo(@NonNull Class<?> cls, boolean isFinishActivity, @NonNull Bundle bundle) {
        startActivity(new Intent(getActivity(), cls).putExtras(bundle));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);      /*      anim in     */
        if (isFinishActivity) finish();
        //  overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);     /*      anim out    */
    }

}
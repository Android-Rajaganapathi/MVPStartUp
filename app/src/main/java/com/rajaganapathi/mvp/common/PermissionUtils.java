package com.rajaganapathi.mvp.common;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.Fragment;

public class PermissionUtils {

    public boolean useRunTimePermissions() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    public boolean hasPermission(Activity activity, String permission) {
        return !useRunTimePermissions() || activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasPermission(Activity activity, String[] permissions) {
        boolean b = true;
        for (String permission : permissions)
            if (!hasPermission(activity, permission))
                return false;
        return b;
    }

    public boolean requestPermissions(Activity activity, String[] permission, int requestCode) {
        if (useRunTimePermissions()) activity.requestPermissions(permission, requestCode);
        return (hasPermission(activity, permission));
    }

    public void requestPermissions(Fragment fragment, String[] permission, int requestCode) {
        if (useRunTimePermissions()) fragment.requestPermissions(permission, requestCode);
    }

    public boolean shouldShowRational(Activity activity, String permission) {
        return useRunTimePermissions() && activity.shouldShowRequestPermissionRationale(permission);
    }

    public boolean shouldAskForPermission(Activity activity, String permission) {
        return useRunTimePermissions() && !hasPermission(activity, permission) && (!hasAskedForPermission(activity, permission) || shouldShowRational(activity, permission));
    }

    public void goToAppSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", activity.getPackageName(), null));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public boolean hasAskedForPermission(Activity activity, String permission) {
        return PreferenceManager.getDefaultSharedPreferences(activity).getBoolean(permission, false);
    }

    public void markedPermissionAsAsked(Activity activity, String permission) {
        PreferenceManager
                .getDefaultSharedPreferences(activity)
                .edit()
                .putBoolean(permission, true)
                .apply();
    }
}

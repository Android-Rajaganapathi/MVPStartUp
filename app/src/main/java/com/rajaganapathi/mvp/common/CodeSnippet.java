package com.rajaganapathi.mvp.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeSnippet {

    private Context mContext;

    public CodeSnippet(Context mContext) {
        this.mContext = mContext;
    }

    public boolean hasNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null)
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) return true;
            else return activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
        return false;
    }

    public boolean isValidPassword(String s) {

        /*
             ^                     # start-of-string
                 (?=.*[0-9])       # a digit must occur at least once
                 (?=.*[a-z])       # a lower case letter must occur at least once
                 (?=.*[A-Z])       # an upper case letter must occur at least once
                 (?=.*[@#$%^&+=])  # a special character must occur at least once you can replace with your special characters
                 (?=\\S+$)         # no whitespace allowed in the entire string
                 .{8,15}           # anything, at least eight to fifteen places
             $                     # end-of-string
         */
        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_+=])(?=\\S+$).{8,15}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(s);

        return matcher.matches();
    }

    public boolean isValidPhoneNumber(String s) {
        if (TextUtils.isEmpty(s)) return false;
        else if (s.length() != 10) return false;
        else return !s.contains(" ");
    }

}


package com.rajaganapathi.mvp.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PreferencesHelper {

    private final SharedPreferences mPrefs;

    public PreferencesHelper(Context context) {
        System.out.println("RRR : Success : Preferences Helper created");
        mPrefs = context.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
    }

    private boolean isKeyExists(String key) {
        Map<String, ?> map = mPrefs.getAll();
        if (map.containsKey(key)) return true;
        else {
            System.out.println("RRR PreferencesHelper: No such KEY " + key);
            return false;
        }
    }

    public void setInt(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
    }

    public int getInt(String key, int defaultValue) {
        if (isKeyExists(key)) return mPrefs.getInt(key, defaultValue);
        return defaultValue;
    }

    public int getInt(String key) {
        if (isKeyExists(key)) return mPrefs.getInt(key, 0);
        return 0;
    }

    public void setBoolean(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (isKeyExists(key)) return mPrefs.getBoolean(key, defaultValue);
        return defaultValue;
    }

    public boolean getBoolean(String key) {
        if (isKeyExists(key)) return mPrefs.getBoolean(key, false);
        return false;
    }

    public void setString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
    }

    public String getString(String key, String defaultValue) {
        if (isKeyExists(key)) return mPrefs.getString(key, defaultValue);
        return defaultValue;
    }

    public String getString(String key) {
        if (isKeyExists(key)) return mPrefs.getString(key, null);
        return null;
    }

    public void setFloat(String key, float value) {
        mPrefs.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key, float defaultValue) {
        if (isKeyExists(key)) return mPrefs.getFloat(key, defaultValue);
        return defaultValue;
    }

    public float getFloat(String key) {
        if (isKeyExists(key)) return mPrefs.getFloat(key, 0);
        return 0;
    }

    public void setLong(String key, long value) {
        mPrefs.edit().putLong(key, value).apply();
    }

    public long getLong(String key, long defaultValue) {
        if (isKeyExists(key)) return mPrefs.getLong(key, defaultValue);
        return defaultValue;
    }

    public long getLong(String key) {
        if (isKeyExists(key)) return mPrefs.getLong(key, 0);
        return 0;
    }

    public void setDouble(String key, double value) {
        mPrefs.edit().putString(key, String.valueOf(value)).apply();
    }

    public double getDouble(String key, double defaultValue) {
        if (isKeyExists(key))
            return Double.parseDouble(Objects.requireNonNull(mPrefs.getString(key, "0")));
        return defaultValue;
    }

    public double getDouble(String key) {
        if (isKeyExists(key))
            return Double.parseDouble(Objects.requireNonNull(mPrefs.getString(key, "0")));
        return 0;
    }

    public <T> void saveObject(String key, T object) {
        mPrefs.edit().putString(key, new Gson().toJson(object)).apply();
    }

    public <T> T getObject(String key, Class<T> classType) {
        if (isKeyExists(key)) {
            String objectString = mPrefs.getString(key, null);
            if (objectString != null) return new Gson().fromJson(objectString, classType);
        }
        return null;
    }

    public <T> void saveObjectsList(String key, List<T> objectList) {
        mPrefs.edit().putString(key, new Gson().toJson(objectList)).apply();
    }

    public <T> List<T> getObjectsList(String key, Class<T> classType) {
        if (isKeyExists(key)) {
            String objectString = mPrefs.getString(key, null);
            if (objectString != null) {

                ArrayList<T> t = new Gson().fromJson(objectString, new TypeToken<List<T>>() {
                }.getType());
                List<T> finalList = new ArrayList<>();

                for (int i = 0; i < t.size(); i++) {
                    String s = String.valueOf(t.get(i));
                    finalList.add(new Gson().fromJson(s, classType));
                }

                return finalList;
            }
        }
        return null;
    }

    public void clearSession() {
        mPrefs.edit().clear().apply();
    }

    public void deleteValue(String key) {
        if (isKeyExists(key)) mPrefs.edit().remove(key).apply();
    }

    public boolean deleteVal(String key) {
        if (isKeyExists(key)) {
            mPrefs.edit().remove(key).apply();
            return true;
        }
        return false;
    }
}

package com.tinygame.lianliankan;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingManager {

    private static SettingManager gSettingManager = new SettingManager();
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    
    public static SettingManager getInstance() {
        return gSettingManager;
    }
    
    public int getLastDiff() {
        return mSharedPreferences.getInt(mContext.getString(R.string.perf_last_diff), 0);
    }
    
    public void setLastDiff(int diff) {
        mEditor.putInt(mContext.getString(R.string.perf_last_diff), diff);
        mEditor.commit();
    }
    
    public int getLastCategory() {
        return mSharedPreferences.getInt(mContext.getString(R.string.perf_last_cate), 0);
    }
    
    public void setLastCategory(int category) {
        mEditor.putInt(mContext.getString(R.string.perf_last_cate), category);
        mEditor.commit();
    }
    
    public void init(Context context) {
        mContext = context;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mEditor = mSharedPreferences.edit();
    }
    
    private SettingManager() {
    }
}
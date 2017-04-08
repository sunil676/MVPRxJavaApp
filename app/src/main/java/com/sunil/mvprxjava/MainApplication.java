package com.sunil.mvprxjava;

import android.app.Application;

import com.sunil.data.source.db.GreenDaoDatabase;

/**
 * Created by 20125908 on 4/7/2017.
 */

public class MainApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        GreenDaoDatabase.getInstance().init(this);
    }
}

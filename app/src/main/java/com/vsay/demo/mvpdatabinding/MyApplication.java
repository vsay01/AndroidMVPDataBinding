package com.vsay.demo.mvpdatabinding;

import android.app.Application;

import com.vsay.demo.mvpdatabinding.components.DaggerNetComponent;
import com.vsay.demo.mvpdatabinding.components.NetComponent;
import com.vsay.demo.mvpdatabinding.modules.AppModule;
import com.vsay.demo.mvpdatabinding.modules.NetModule;
import com.vsay.demo.mvpdatabinding.utils.Constants;

/**
 * Created by vsaya on 2/11/17.
 */

public class MyApplication extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Constants.EVENT_FEED_URL))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
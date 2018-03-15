package com.twitchexplorer.twitchexplorer.view.application;


import android.app.Activity;
import android.app.Application;

import com.twitchexplorer.twitchexplorer.lib.dagger.component.ApplicationComponent;
import com.twitchexplorer.twitchexplorer.lib.dagger.component.DaggerApplicationComponent;
import com.twitchexplorer.twitchexplorer.lib.dagger.module.ApplicationModule;


public class MainApplication extends Application {

    ApplicationComponent build;

    @Override
    public void onCreate() {
        super.onCreate();

        // Only need to be set with constructor arguments
        build = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }


    public static ApplicationComponent get(Activity activity) {
        return ((MainApplication) activity.getApplication()).build;
    }

}

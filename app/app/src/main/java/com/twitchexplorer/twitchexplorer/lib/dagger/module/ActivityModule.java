package com.twitchexplorer.twitchexplorer.lib.dagger.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


import com.twitchexplorer.twitchexplorer.lib.dagger.qualifier.ActivityContextQualifier;
import com.twitchexplorer.twitchexplorer.lib.dagger.scope.ActivityScope;
import com.twitchexplorer.twitchexplorer.model.service.FragmentService;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public FragmentManager fragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @Provides
    @ActivityScope
    @ActivityContextQualifier
    // qualifier used when multiple providers in multiple with the same type
    public Context getContext() {
        return activity.getBaseContext();
    }


    @Provides
    @ActivityScope
    public FragmentService fragmentService(FragmentManager fragmentManager) {
        return new FragmentService(fragmentManager);
    }


}

package com.twitchexplorer.twitchexplorer.lib.dagger.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.twitchexplorer.twitchexplorer.lib.dagger.qualifier.ApplicationContextQualifier;
import com.twitchexplorer.twitchexplorer.lib.dagger.scope.ApplicationScope;
import com.twitchexplorer.twitchexplorer.model.service.GsonService;
import com.twitchexplorer.twitchexplorer.model.service.PermissionService;
import com.twitchexplorer.twitchexplorer.model.service.RestApiService;
import com.twitchexplorer.twitchexplorer.model.service.WebSocketService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(@NonNull Application application) {
        context = application.getApplicationContext();
    }

    @Provides
    @ApplicationScope
    public RestApiService restApiService() {
        return new RestApiService(context);
    }

    @Provides
    @ApplicationScope
    @ApplicationContextQualifier
    public Context context() {
        return context;
    }

    @Provides
    @ApplicationScope
    public GsonService gsonService() {
        return new GsonService();
    }


    @Provides
    @ApplicationScope
    public PermissionService permissionService() {
        return new PermissionService();
    }

    @Provides
    @ApplicationScope
    public WebSocketService webSocketService(RestApiService restApiService){
        return new WebSocketService(restApiService);
    }

}

package com.twitchexplorer.twitchexplorer.lib.dagger.component;


import android.content.Context;

import com.twitchexplorer.twitchexplorer.model.service.FragmentService;
import com.twitchexplorer.twitchexplorer.model.service.RestApiService;
import com.twitchexplorer.twitchexplorer.view.activity.MainActivity;
import com.twitchexplorer.twitchexplorer.lib.dagger.module.ActivityModule;
import com.twitchexplorer.twitchexplorer.lib.dagger.qualifier.ActivityContextQualifier;
import com.twitchexplorer.twitchexplorer.lib.dagger.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    @ActivityContextQualifier
    Context context();

    RestApiService restApiService();

    FragmentService fragmentService();
    // PermissionService permissionService();
    //Appliaction Level
    /*
    GoogleApiService googleApiService();
    com.alexanderaftrolle.runchallenge.model.service.networkV2.google.GoogleApiService googleApiService2();
    GameEngine gameEngine();
    AudioService audioService();
    SettingsService settingsService();
    GameService gameService();
    RoomService gameRoomService();
   @ActivityContextQualifier Context context();
    GoogleInviteService googleInviteService();



    //Activity Level
    FragmentHandler fragmentHandler();
    */
    void inject(MainActivity mainActivity);
}

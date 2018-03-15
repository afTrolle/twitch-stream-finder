package com.twitchexplorer.twitchexplorer.lib.dagger.module;


import dagger.Module;

/**
 * Holds Fragment Controllers
 * Observer these will be created when fragment is created! So often recreated!
 * Observer that Probably want to put GameController in application instead!
 */

@Module
public class FragmentModule {

/*
   @Provides
    @RunChallengeFragmentScope
    public HomeController homeController(GoogleApiService googleApiService, GameRoomService gameRoomService, FragmentHandler fragmentHandler) {
        HomeController homeController = new HomeController(googleApiService, fragmentHandler, gameRoomService);
        Log.d(homeController.toString(), "Dagger provides homeController");
        return homeController;
    }


    @Provides
    @RunChallengeFragmentScope
    public LoginController loginCtrl(com.alexanderaftrolle.runchallenge.model.service.networkV2.google.GoogleApiService googleApiService) {
        return new LoginController(googleApiService);
    }

     */
}

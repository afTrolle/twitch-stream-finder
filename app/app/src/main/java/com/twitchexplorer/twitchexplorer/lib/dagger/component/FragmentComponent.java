package com.twitchexplorer.twitchexplorer.lib.dagger.component;

import com.twitchexplorer.twitchexplorer.lib.dagger.module.FragmentModule;
import com.twitchexplorer.twitchexplorer.lib.dagger.scope.FragmentScope;
import com.twitchexplorer.twitchexplorer.view.fragment.HomeFragment;
import com.twitchexplorer.twitchexplorer.view.fragment.SearchFragment;
import com.twitchexplorer.twitchexplorer.view.fragment.StreamViewFragment;
import com.twitchexplorer.twitchexplorer.view.fragment.UserFragment;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = ActivityComponent.class)
public interface FragmentComponent {
    void inject(HomeFragment fragment);

    void inject(SearchFragment fragment);

    void inject(StreamViewFragment streamViewFragment);

    void inject(UserFragment userFragment);

    // void inject(StreamViewFragment streamViewFragment);
    /*
    void inject(LoginFragment loginFragment);
    void inject(HomeFragment fragment);
    void inject(GameFragment gameFragment);
    void inject(PlayFragment playFragment);
    */
}


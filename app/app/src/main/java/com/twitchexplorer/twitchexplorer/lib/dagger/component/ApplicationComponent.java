package com.twitchexplorer.twitchexplorer.lib.dagger.component;


import com.twitchexplorer.twitchexplorer.lib.dagger.module.ApplicationModule;
import com.twitchexplorer.twitchexplorer.lib.dagger.scope.ApplicationScope;
import com.twitchexplorer.twitchexplorer.model.service.FragmentService;
import com.twitchexplorer.twitchexplorer.model.service.GsonService;
import com.twitchexplorer.twitchexplorer.model.service.PermissionService;
import com.twitchexplorer.twitchexplorer.model.service.RestApiService;
import com.twitchexplorer.twitchexplorer.model.service.WebSocketService;

import dagger.Component;

//use { ,} with multiple modules.

@ApplicationScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    //Exposes
    GsonService gsonService();

    RestApiService restApiService();

    PermissionService permissionService();

    WebSocketService WebSocketService();

    //Dagger2 Tutorial https://www.youtube.com/watch?v=WAENNp2wxbQ
    //Helped also https://github.com/MindorksOpenSource/android-dagger2-example
}

package com.twitchexplorer.twitchexplorer.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twitchexplorer.twitchexplorer.R;
import com.twitchexplorer.twitchexplorer.lib.dagger.component.ActivityComponent;
import com.twitchexplorer.twitchexplorer.lib.dagger.component.DaggerActivityComponent;
import com.twitchexplorer.twitchexplorer.lib.dagger.module.ActivityModule;
import com.twitchexplorer.twitchexplorer.model.service.FragmentService;
import com.twitchexplorer.twitchexplorer.view.application.MainApplication;
import com.twitchexplorer.twitchexplorer.view.fragment.SearchFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private ActivityComponent component;

    @Inject
    FragmentService fragmentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        component = DaggerActivityComponent.builder()
                .applicationComponent(MainApplication.get(this))
                .activityModule(new ActivityModule(this))
                .build();
        component.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // if first start
        if (savedInstanceState == null) {
            if (null == fragmentService.getCurrentFragment())
                fragmentService.replaceFragment(new SearchFragment(), false);
        }
    }

    public ActivityComponent getDaggerComponent() {
        return component;
    }
}

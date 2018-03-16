package com.twitchexplorer.twitchexplorer.view.fragment;

import com.twitchexplorer.twitchexplorer.R;
import com.twitchexplorer.twitchexplorer.lib.dagger.component.FragmentComponent;

public class UserFragment extends BaseFragment {

    @Override
    int getViewLayout() {
        return R.layout.user_layout;
    }

    @Override
    void initDagger(FragmentComponent component) {
        component.inject(this);
    }


}

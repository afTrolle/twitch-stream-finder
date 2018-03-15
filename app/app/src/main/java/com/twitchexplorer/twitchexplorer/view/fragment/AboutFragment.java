package com.twitchexplorer.twitchexplorer.view.fragment;

import com.twitchexplorer.twitchexplorer.R;
import com.twitchexplorer.twitchexplorer.lib.dagger.component.FragmentComponent;



public class AboutFragment extends BaseFragment {
    @Override
    int getViewLayout() {
        return R.layout.empty_layout;
    }

    @Override
    void initDagger(FragmentComponent component) {

    }
}

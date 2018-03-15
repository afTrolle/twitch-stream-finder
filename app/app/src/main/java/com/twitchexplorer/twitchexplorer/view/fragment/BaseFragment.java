package com.twitchexplorer.twitchexplorer.view.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitchexplorer.twitchexplorer.lib.dagger.component.DaggerFragmentComponent;
import com.twitchexplorer.twitchexplorer.lib.dagger.component.FragmentComponent;
import com.twitchexplorer.twitchexplorer.view.activity.MainActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {

    abstract @LayoutRes
    int getViewLayout();

    private Unbinder unbinder;

    abstract void initDagger(FragmentComponent component);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentComponent component = DaggerFragmentComponent.builder().activityComponent(((MainActivity) getActivity()).getDaggerComponent()).build();
        initDagger(component);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getViewLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

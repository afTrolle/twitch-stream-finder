package com.twitchexplorer.twitchexplorer.model.service;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.twitchexplorer.twitchexplorer.R;
import com.twitchexplorer.twitchexplorer.view.fragment.HomeFragment;


public class FragmentService {
    private final FragmentManager fragmentManager;
    private final static int containerViewResId = R.id.fragment_container;
    private Fragment activeFragment;

    public FragmentService(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public Fragment getCurrentFragment() {
        if (activeFragment == null) {
            activeFragment = (fragmentManager.getFragments().size() > 0) ? fragmentManager.getFragments().get(0) : null;
        }
        return activeFragment;
    }

    public void replaceFragment(Fragment fragment, boolean addOnBackStack) {
        if (fragmentManager == null)
            return;

        FragmentTransaction transaction = fragmentManager.beginTransaction().replace(containerViewResId, fragment);
        if (addOnBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
        activeFragment = fragment;
    }

    public void clearBackStack() {
        if (fragmentManager == null)
            return;

        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        activeFragment = null;
    }
}

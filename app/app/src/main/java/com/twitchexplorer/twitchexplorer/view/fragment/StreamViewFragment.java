package com.twitchexplorer.twitchexplorer.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.twitchexplorer.twitchexplorer.R;
import com.twitchexplorer.twitchexplorer.lib.dagger.component.FragmentComponent;
import com.twitchexplorer.twitchexplorer.lib.utils.SnackbarHelper;
import com.twitchexplorer.twitchexplorer.model.pojo.LiveStreamUserVoteView;
import com.twitchexplorer.twitchexplorer.model.service.RestApiService;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class StreamViewFragment extends BaseFragment {

    private final static String bundleKey = "params";

    @Inject
    RestApiService restApiService;

    @Override
    int getViewLayout() {
        return R.layout.stream_view;
    }

    @Override
    void initDagger(FragmentComponent component) {
        component.inject(this);
    }

    @BindView(R.id.stream_progress_bar)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Gson gson = new Gson();
        RestApiService.SearchParams params = gson.fromJson(getArguments().getString(bundleKey), RestApiService.SearchParams.class);
        searchForStreams(params);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void searchForStreams(RestApiService.SearchParams params) {
        restApiService.searchStreams(new RestApiService.RestResponse<List<LiveStreamUserVoteView>>() {
            @Override
            public void onResponse(List<LiveStreamUserVoteView> response) {
                progressBar.setVisibility(View.GONE);
                SnackbarHelper.showWarning(getView(), "search found: " + response.size());
            }
        }, new RestApiService.RestError() {
            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        }, params);
    }
    
    public static Fragment newInstance(RestApiService.SearchParams params) {
        StreamViewFragment fragment = new StreamViewFragment();
        Bundle bundle = new Bundle();
        Gson gson = new Gson();
        bundle.putString(bundleKey, gson.toJson(params));
        fragment.setArguments(bundle);
        return fragment;
    }
}

package com.twitchexplorer.twitchexplorer.view.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.twitchexplorer.twitchexplorer.R;
import com.twitchexplorer.twitchexplorer.lib.dagger.component.FragmentComponent;
import com.twitchexplorer.twitchexplorer.model.pojo.LiveStreamUserVoteView;
import com.twitchexplorer.twitchexplorer.model.service.FragmentService;
import com.twitchexplorer.twitchexplorer.model.service.RestApiService;
import com.twitchexplorer.twitchexplorer.model.service.WebSocketService;
import com.twitchexplorer.twitchexplorer.view.view.adapter.StreamsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class StreamViewFragment extends BaseFragment implements WebSocketService.WebSocketCallback {

    private final static String bundleKey = "params";

    @Inject
    FragmentService fragmentService;

    @Inject
    RestApiService restApiService;

    @BindView(R.id.stream_recycler_view)
    RecyclerView recyclerView;

    @Inject
    WebSocketService webSocketService;

    @BindView(R.id.search_fab)
    FloatingActionButton mFab;

    private StreamsAdapter mAdapter;

    private RestApiService.SearchParams params;

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

    boolean isVisible = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //  recyclerView.setHasFixedSize(true);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFab.show();
            }
        }, 500);


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        Gson gson = new Gson();
        params = gson.fromJson(getArguments().getString(bundleKey), RestApiService.SearchParams.class);
        searchForStreams(params);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentService.replaceFragment(new SearchFragment(), false);
            }
        });
    }

    private void searchForStreams(RestApiService.SearchParams params) {
        restApiService.searchStreams(new RestApiService.RestResponse<List<LiveStreamUserVoteView>>() {
            @Override
            public void onResponse(List<LiveStreamUserVoteView> response, int code) {
                if (progressBar == null)
                    return;
                progressBar.setVisibility(View.GONE);
                mAdapter = new StreamsAdapter(response, new StreamsAdapter.OnClickListener() {
                    @Override
                    public void onClick(LiveStreamUserVoteView viewData) {

                        UserFragment userFragment = UserFragment.newInstance(viewData.getUserId());
                        fragmentService.replaceFragment(userFragment, true);

                    }
                });
                recyclerView.setAdapter(mAdapter);
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

    @Override
    public void onResume() {
        super.onResume();
        webSocketService.setCallback(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        webSocketService.setCallback(null);
    }

    //called with updates
    @Override
    public void onUpdate(WebSocketService.UpdateObject updateObject) {
        if (updateObject != null) {
            for (Long streamId : updateObject.streamIds) {
                if (mAdapter.isStreamId(streamId)) {
                    searchForStreams(params);
                }
            }
        }
    }
}

package com.twitchexplorer.twitchexplorer.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.appyvet.materialrangebar.RangeBar;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.twitchexplorer.twitchexplorer.R;
import com.twitchexplorer.twitchexplorer.lib.dagger.component.FragmentComponent;
import com.twitchexplorer.twitchexplorer.lib.utils.LanuageHelper;
import com.twitchexplorer.twitchexplorer.model.pojo.BroadcasterType;
import com.twitchexplorer.twitchexplorer.model.pojo.GamesLive;
import com.twitchexplorer.twitchexplorer.model.pojo.Language;
import com.twitchexplorer.twitchexplorer.model.pojo.StreamType;
import com.twitchexplorer.twitchexplorer.model.pojo.UserType;
import com.twitchexplorer.twitchexplorer.model.service.FragmentService;
import com.twitchexplorer.twitchexplorer.model.service.RestApiService;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchFragment extends BaseFragment {

    @Inject
    RestApiService restApiService;

    @Inject
    FragmentService fragmentService;


    private List<BroadcasterType> broadcasterTypes;
    private List<Language> languages;
    private List<GamesLive> gamesLiveList;
    private List<StreamType> streamTypeList;
    private List<UserType> userTypeList;

    @Override
    int getViewLayout() {
        return R.layout.search_view;
    }

    @Override
    void initDagger(FragmentComponent component) {
        component.inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSpinners();
    }

    private void initSpinners() {

        restApiService.getGamesBeingPlayed(new RestApiService.RestResponse<List<GamesLive>>() {
            @Override
            public void onResponse(List<GamesLive> response) {
                gamesLiveList = response;
                ArrayList<String> gameNames = new ArrayList<>(response.size());
                gameNames.add("Ignore");
                for (GamesLive gamesLive : response) {
                    gameNames.add(gamesLive.getName());
                }
                setSpinnerData(gameNames, gameSpinner);
            }
        }, restError);

        restApiService.getLanguages(new RestApiService.RestResponse<List<Language>>() {
            @Override
            public void onResponse(List<Language> response) {
                languages = response;
                List<String> languages = LanuageHelper.help(response);
                languages.add(0, "Ignore");
                setSpinnerData(languages, streamLanguageSpinner);
            }
        }, restError);

        restApiService.getBroadCasterTypes(new RestApiService.RestResponse<List<BroadcasterType>>() {
            @Override
            public void onResponse(List<BroadcasterType> response) {
                broadcasterTypes = response;
                List<String> types = new ArrayList<>();
                types.add("Ignore");
                for (BroadcasterType broadcasterType : response) {
                    types.add(broadcasterType.getType());
                }
                setSpinnerData(types, broadCasterTypeSpinner);
            }
        }, restError);

        restApiService.getStreamTypes(new RestApiService.RestResponse<List<StreamType>>() {
            @Override
            public void onResponse(List<StreamType> response) {
                streamTypeList = response;
                ArrayList<String> types = new ArrayList<>();
                types.add("Ignore");
                for (StreamType streamType : response) {
                    types.add(streamType.getName());
                }
                setSpinnerData(types, streamTypeSpinner);
            }
        }, restError);

        restApiService.getUserTypes(new RestApiService.RestResponse<List<UserType>>() {
            @Override
            public void onResponse(List<UserType> response) {
                userTypeList = response;
                ArrayList<String> list = new ArrayList<>();
                list.add("Ignore");
                for (UserType userType : response) {
                    list.add(userType.getType());
                }
                setSpinnerData(list, userTypeSpinner);
            }
        }, restError);


    }

    private void setSpinnerData(List<String> spinnerData, MaterialBetterSpinner spinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, spinnerData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setText(spinnerData.get(0));
    }

    /* stream settings */

    @BindView(R.id.search_stream_title)
    MaterialEditText streamTitleEdit;

    @BindView(R.id.search_stream_min_view_count)
    MaterialEditText minViewCountEdit;

    @BindView(R.id.search_stream_max_view_count)
    MaterialEditText maxViewCountEdit;

    @BindView(R.id.search_stream_game_spinner)
    MaterialBetterSpinner gameSpinner;

    @BindView(R.id.search_stream_stream_type_spinner)
    MaterialBetterSpinner streamTypeSpinner;

    @BindView(R.id.search_stream_language_spinner)
    MaterialBetterSpinner streamLanguageSpinner;

    @BindView(R.id.search_stream_stream_min_time)
    MaterialEditText minStreamTime;

    @BindView(R.id.search_stream_stream_max_time)
    MaterialEditText maxStreamTime;

    /* user settings */

    @BindView(R.id.search_user_name)
    MaterialEditText userNameEdit;

    @BindView(R.id.search_user_description)
    MaterialEditText userDescriptionEdit;

    @BindView(R.id.search_user_type_spinner)
    MaterialBetterSpinner userTypeSpinner;

    @BindView(R.id.search_user_rating_range_bar)
    RangeBar ratingRangeBar;

    @BindView(R.id.search_user_broad_caster_type)
    MaterialBetterSpinner broadCasterTypeSpinner;

    @BindView(R.id.search_user_min_follower_count)
    MaterialEditText minFollowerCount;

    @BindView(R.id.search_user_max_follower_count)
    MaterialEditText maxFollowerCount;

    @OnClick(R.id.search_stream_button)
    public void SearchClicked() {

        RestApiService.SearchParams params = new RestApiService.SearchParams();
        String title = streamTitleEdit.getText().toString();
        if (!title.isEmpty())
            params.setStreamTitle(title);

        String minViewCount = minViewCountEdit.getText().toString();
        if (!minViewCount.isEmpty()) {
            params.setMinViewCount(Integer.valueOf(minViewCount));
        }
        String maxViewCount = maxViewCountEdit.getText().toString();
        if (!minViewCount.isEmpty()) {
            params.setMaxViewCount(Integer.valueOf(maxViewCount));
        }

        String s = gameSpinner.getText().toString();
        if (!s.isEmpty() && checkIgnore(minViewCount)) {
            for (GamesLive gamesLive : gamesLiveList) {
                if (gamesLive.getName().equals(s)) {
                    params.setGameID(gamesLive.getGameId());
                }
            }
        }

        String lan = streamLanguageSpinner.getText().toString();
        if (!lan.isEmpty() && checkIgnore(lan)) {
            for (Language gamesLive : languages) {
                if (gamesLive.getName().equals(lan)) {
                    params.setLanguageId(gamesLive.getLanguageId());
                }
            }
        }

        String stpStr = streamTypeSpinner.getText().toString();
        if (!stpStr.isEmpty() && checkIgnore(stpStr)) {
            for (StreamType gamesLive : streamTypeList) {
                if (gamesLive.getName().equals(stpStr)) {
                    params.setStreamTypeId(gamesLive.getStreamTypeId());
                }
            }
        }

        String minMinsOnline = minStreamTime.getText().toString();
        if (!minMinsOnline.isEmpty()) {
            params.setMinStreamTime(Integer.parseInt(minMinsOnline));
        }
        String maxMinsOnline = maxStreamTime.getText().toString();
        if (!maxMinsOnline.isEmpty()) {
            params.setMaxStreamTime(Integer.parseInt(maxMinsOnline));
        }

        String userName = userNameEdit.getText().toString();
        if (!userName.isEmpty()) {
            params.setUserName(userName);
        }

        String userDesc = userDescriptionEdit.getText().toString();
        if (!userDesc.isEmpty()) {
            params.setUserDescription(userDesc);
        }

        String userType = userTypeSpinner.getText().toString();
        if (!userType.isEmpty() && checkIgnore(userType)) {
            for (UserType type : userTypeList) {
                if (type.getType().equals(userType))
                    params.setUserTypeId(type.getUserTypeId());
            }
        }

        float tickStart = ratingRangeBar.getTickStart();
        float tickEnd = ratingRangeBar.getTickEnd();
        if (tickStart != 0 && tickEnd != 1) {
            float lowest = tickStart > tickEnd ? tickEnd : tickStart;
            float highest = tickStart > tickEnd ? tickStart : tickEnd;
            params.setMinVoteRatio((double) lowest);
            params.setMaxVoteRatio((double) highest);
        }

        String broadCasterString = broadCasterTypeSpinner.getText().toString();
        if (!broadCasterString.isEmpty()){
            for (BroadcasterType broadcasterType : broadcasterTypes) {
                if (broadcasterType.getType().equals(broadCasterString))
                    params.setBroadCasterTypeId(broadcasterType.getBroadcasterTypeId());
            }


        }

        String minFollowerStr = minFollowerCount.getText().toString();
        String maxFollowerStr = maxFollowerCount.getText().toString();
        if (!minFollowerStr.isEmpty()) {
            params.setMinFollowerCount(Integer.valueOf(minFollowerStr));
        }
        if (!maxFollowerStr.isEmpty()) {
            params.setMaxFollowerCount(Integer.valueOf(maxFollowerStr));
        }

        fragmentService.replaceFragment(StreamViewFragment.newInstance(params), false);
    }

    private boolean checkIgnore(String s) {
        return !s.equals("Ignore");
    }

    /*
     * Called when an error occurred, in restApi Call
     */
    RestApiService.RestError restError = new RestApiService.RestError() {
        @Override
        public void onError(Exception e) {
            e.printStackTrace();
        }
    };
}

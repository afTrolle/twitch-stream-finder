package com.twitchexplorer.twitchexplorer.view.fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twitchexplorer.twitchexplorer.R;
import com.twitchexplorer.twitchexplorer.lib.dagger.component.FragmentComponent;
import com.twitchexplorer.twitchexplorer.lib.utils.SnackbarHelper;
import com.twitchexplorer.twitchexplorer.lib.utils.TwitchHelper;
import com.twitchexplorer.twitchexplorer.model.pojo.GamesLive;
import com.twitchexplorer.twitchexplorer.model.pojo.UserInfoView;
import com.twitchexplorer.twitchexplorer.model.service.RestApiService;
import com.twitchexplorer.twitchexplorer.model.service.WebSocketService;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class UserFragment extends BaseFragment implements RestApiService.RestError, RestApiService.RestResponse<UserInfoView>, WebSocketService.WebSocketCallback {

    private final static String KEY = "UserID";

    @BindView(R.id.user_stream_preview_image_view)
    ImageView streamPreview;

    @BindView(R.id.user_card_profile_image)
    ImageView userProfileImage;

    @BindView(R.id.user_stream_title)
    TextView streamTile;

    @BindView(R.id.user_stream_game_name)
    TextView streamGame;

    @BindView(R.id.user_user_name)
    TextView userName;

    @BindView(R.id.user_user_rating)
    TextView userRating;

    @BindView(R.id.user_user_description)
    TextView userDescription;

    // down vote

    @BindView(R.id.user_vote_down_count_text)
    TextView numberOfDownVotes;

    // up vote

    @BindView(R.id.user_vote_up_count_text)
    TextView numberOfUpVotes;

    @Inject
    RestApiService restApiService;

    @Inject
    WebSocketService socketService;

    private long userId;
    private String name;

    @Override
    int getViewLayout() {
        return R.layout.user_layout;
    }

    @Override
    void initDagger(FragmentComponent component) {
        component.inject(this);
    }

    public static UserFragment newInstance(long userID) {
        Bundle args = new Bundle();
        args.putLong(KEY, userID);
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getArguments().getLong(KEY, -1);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (userId != -1)
            restApiService.getUser(this, this, userId);
    }

    @Override
    public void onResume() {
        super.onResume();
        socketService.setCallback(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        socketService.setCallback(null);
    }

    @Override
    public void onError(Exception e) {
        //TODO show error message close app or something
        e.printStackTrace();
    }

    @Override
    public void onResponse(UserInfoView response, int code) {

        BigInteger bigInteger = response.getPositiveVotes();
        if (bigInteger != null) {
            numberOfUpVotes.setText(bigInteger.toString());
        } else {
            numberOfUpVotes.setText("0");
        }
        bigInteger = response.getNegativeVotes();
        if (bigInteger != null) {
            numberOfDownVotes.setText(response.getNegativeVotes().toString());
        } else {
            numberOfDownVotes.setText("0");
        }
        float ratio = response.getRatio() == null ? 0f : response.getRatio().floatValue();
        userRating.setText(toPercentage(ratio, 2));
        name = response.getName();
        userName.setText(response.getName());
        userDescription.setText(response.getDescription());

        String profileImageUrl = response.getProfileImage();
        if (profileImageUrl != null && !profileImageUrl.isEmpty())
            Picasso.get().load(profileImageUrl).into(userProfileImage);

        String thumbnail = response.getThumbnail();
        if (thumbnail != null) {
            String thumbUrl = thumbnail.replace("{width}", "480").replace("{height}", "270");
            Picasso.get().load(thumbUrl).into(streamPreview);
        }

        String streamTitle = response.getTitle();
        if (streamTitle != null)
            streamTile.setText(streamTitle);

        final Integer gameId = response.getGameId();
        if (gameId != null && gameId > 0)
            restApiService.getGamesBeingPlayed(new RestApiService.RestResponse<List<GamesLive>>() {
                @Override
                public void onResponse(List<GamesLive> response, int code) {
                    for (GamesLive gamesLive : response) {
                        if (gamesLive.getGameId().equals(gameId)) {
                            streamGame.setText(gamesLive.getName());
                            return;
                        }
                    }

                }
            }, new RestApiService.RestError() {
                @Override
                public void onError(Exception e) {

                }
            });
    }

    @BindView(R.id.user_thumb_down_button)
    ImageButton thumbDownButton;

    @OnClick(R.id.user_thumb_down_button)
    public void onThumbDown() {
        restApiService.vote(new RestApiService.RestResponse<String>() {
            @Override
            public void onResponse(String response, int code) {
                thumbDownButton.setImageTintList(ColorStateList.valueOf(Color.RED));

                SnackbarHelper.showWarning(getView(), "Code: " + code + " response: " + response);
            }
        }, new RestApiService.RestError() {
            @Override
            public void onError(Exception e) {

            }
        }, userId, false);
    }

    @BindView(R.id.user_thumb_up_button)
    ImageButton thumbUpButton;


    @OnClick(R.id.user_thumb_up_button)
    public void onThumbUp() {
        restApiService.vote(new RestApiService.RestResponse<String>() {
            @Override
            public void onResponse(String response, int code) {
                thumbUpButton.setImageTintList(ColorStateList.valueOf(Color.GREEN));

                if (code != 200)
                    SnackbarHelper.showWarning(getView(), "Code: " + code + " response: " + response);

            }
        }, this, userId, true);
    }

    public static String toPercentage(float n, int digits) {
        return String.format("%." + digits + "f", n * 100) + "%";
    }

    @Override
    public void onUpdate(WebSocketService.UpdateObject updateObject) {
        if (updateObject.userIds != null && updateObject.userIds.contains(userId)) {
            if (userId != -1) {
                restApiService.getUser(this, this, userId);
            }
        }
    }

    @OnClick({R.id.user_stream_preview_image_view,R.id.user_profile_card})
    public void onStreamPreviewClick() {
        Intent intent = TwitchHelper.start(getContext(), name);
        startActivity(intent);

    }
}

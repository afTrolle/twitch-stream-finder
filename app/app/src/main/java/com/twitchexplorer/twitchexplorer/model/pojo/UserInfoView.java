package com.twitchexplorer.twitchexplorer.model.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;


@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserInfoView implements Serializable {

    private static final long serialVersionUID = -1033368288;

    private final Long       userId;
    private final String     name;
    private final String     description;
    private final Integer    userTypeId;
    private final Integer    broadcasterTypeId;
    private final String     offlineImage;
    private final String     profileImage;
    private final Long       totalViews;
    private final Long       streamId;
    private final String     title;
    private final Timestamp started;
    private final String     thumbnail;
    private final Integer    viewCount;
    private final Integer    gameId;
    private final Integer    streamTypeId;
    private final Integer    languageId;
    private final BigDecimal ratio;
    private final BigInteger positiveVotes;
    private final BigInteger negativeVotes;

    public UserInfoView(UserInfoView value) {
        this.userId = value.userId;
        this.name = value.name;
        this.description = value.description;
        this.userTypeId = value.userTypeId;
        this.broadcasterTypeId = value.broadcasterTypeId;
        this.offlineImage = value.offlineImage;
        this.profileImage = value.profileImage;
        this.totalViews = value.totalViews;
        this.streamId = value.streamId;
        this.title = value.title;
        this.started = value.started;
        this.thumbnail = value.thumbnail;
        this.viewCount = value.viewCount;
        this.gameId = value.gameId;
        this.streamTypeId = value.streamTypeId;
        this.languageId = value.languageId;
        this.ratio = value.ratio;
        this.positiveVotes = value.positiveVotes;
        this.negativeVotes = value.negativeVotes;
    }

    public UserInfoView(
            Long       userId,
            String     name,
            String     description,
            Integer    userTypeId,
            Integer    broadcasterTypeId,
            String     offlineImage,
            String     profileImage,
            Long       totalViews,
            Long       streamId,
            String     title,
            Timestamp  started,
            String     thumbnail,
            Integer    viewCount,
            Integer    gameId,
            Integer    streamTypeId,
            Integer    languageId,
            BigDecimal ratio,
            BigInteger positiveVotes,
            BigInteger negativeVotes
    ) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.userTypeId = userTypeId;
        this.broadcasterTypeId = broadcasterTypeId;
        this.offlineImage = offlineImage;
        this.profileImage = profileImage;
        this.totalViews = totalViews;
        this.streamId = streamId;
        this.title = title;
        this.started = started;
        this.thumbnail = thumbnail;
        this.viewCount = viewCount;
        this.gameId = gameId;
        this.streamTypeId = streamTypeId;
        this.languageId = languageId;
        this.ratio = ratio;
        this.positiveVotes = positiveVotes;
        this.negativeVotes = negativeVotes;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getUserTypeId() {
        return this.userTypeId;
    }

    public Integer getBroadcasterTypeId() {
        return this.broadcasterTypeId;
    }

    public String getOfflineImage() {
        return this.offlineImage;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

    public Long getTotalViews() {
        return this.totalViews;
    }

    public Long getStreamId() {
        return this.streamId;
    }

    public String getTitle() {
        return this.title;
    }

    public Timestamp getStarted() {
        return this.started;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public Integer getViewCount() {
        return this.viewCount;
    }

    public Integer getGameId() {
        return this.gameId;
    }

    public Integer getStreamTypeId() {
        return this.streamTypeId;
    }

    public Integer getLanguageId() {
        return this.languageId;
    }

    public BigDecimal getRatio() {
        return this.ratio;
    }

    public BigInteger getPositiveVotes() {
        return this.positiveVotes;
    }

    public BigInteger getNegativeVotes() {
        return this.negativeVotes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserInfoView (");

        sb.append(userId);
        sb.append(", ").append(name);
        sb.append(", ").append(description);
        sb.append(", ").append(userTypeId);
        sb.append(", ").append(broadcasterTypeId);
        sb.append(", ").append(offlineImage);
        sb.append(", ").append(profileImage);
        sb.append(", ").append(totalViews);
        sb.append(", ").append(streamId);
        sb.append(", ").append(title);
        sb.append(", ").append(started);
        sb.append(", ").append(thumbnail);
        sb.append(", ").append(viewCount);
        sb.append(", ").append(gameId);
        sb.append(", ").append(streamTypeId);
        sb.append(", ").append(languageId);
        sb.append(", ").append(ratio);
        sb.append(", ").append(positiveVotes);
        sb.append(", ").append(negativeVotes);

        sb.append(")");
        return sb.toString();
    }
}

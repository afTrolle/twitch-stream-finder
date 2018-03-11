package twitch.explorer.database;


import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import twitch.explorer.database.jooq.gen.Tables;
import twitch.explorer.database.jooq.gen.enums.VoteState;
import twitch.explorer.database.jooq.gen.tables.UserType;
import twitch.explorer.database.jooq.gen.tables.records.*;
import twitch.explorer.scraper.twitchApi.json.follower.Follows;
import twitch.explorer.scraper.twitchApi.json.games.Game;
import twitch.explorer.settings.Config;

import java.sql.*;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static twitch.explorer.database.jooq.gen.Tables.GAME;
import static twitch.explorer.database.jooq.gen.Tables.LIVE_LONGEST_TIME_SINCE_FOLLOWER_UPDATE_VIEW;
import static twitch.explorer.database.jooq.gen.Tables.STREAM;
import static twitch.explorer.database.jooq.gen.tables.BroadcasterType.BROADCASTER_TYPE;
import static twitch.explorer.database.jooq.gen.tables.Followers.FOLLOWERS;
import static twitch.explorer.database.jooq.gen.tables.GamesLive.GAMES_LIVE;
import static twitch.explorer.database.jooq.gen.tables.User.USER;


public class JooqHandler {

    //Jooq MySql Connection
    private Connection conn;
    private DSLContext create;
    private Config config;
    private static JooqHandler jooqHandler;

    public static JooqHandler get() throws SQLException {
        if (jooqHandler == null)
            jooqHandler = new JooqHandler();
        return jooqHandler;
    }

    public JooqHandler() throws SQLException {
        config = Config.get();
        String userName = config.getMySqlUserName();
        String password = config.getMySqlPassword();
        String url = "jdbc:mysql://" + config.getMySqlUrl();

        // Connection is the only JDBC resource that we need
        conn = DriverManager.getConnection(url, userName, password);
        create = DSL.using(conn, SQLDialect.MYSQL);
    }

    private void getGames() {
        Result<GameRecord> gameRecord = create.selectFrom(GAME).fetch();
        for (GameRecord r : gameRecord) {
            String awesome = r.getName();
        }
    }

    private synchronized GameRecord createGame(int gameId, String name, String artUrl) {
        GameRecord record = create.newRecord(GAME);
        record.setGameId(gameId);
        record.setName(name);
        record.setArtUrl(artUrl);
        record.store();
        return record;
    }

    public synchronized GameRecord getGameById(String gameId) {
        return create.selectFrom(GAME).where(GAME.GAME_ID.eq(Integer.parseInt(gameId))).fetchOne();
    }

    public synchronized UserRecord getUser(String userId) {
        return create.selectFrom(Tables.USER).where(Tables.USER.USER_ID.eq(Long.parseLong(userId))).fetchOne();
    }

    public synchronized boolean existsUser(String userId) {
        return false;
    }

    public synchronized LanguageRecord getLanguage(String language) {
        return create.selectFrom(Tables.LANGUAGE).where(Tables.LANGUAGE.NAME.eq(language)).fetchOne();
    }

    public synchronized LanguageRecord createLanguage(String language) {
        LanguageRecord record = create.newRecord(Tables.LANGUAGE);
        record.setName(language);
        record.store();
        return record;
    }

    public synchronized StreamTypeRecord getStreamType(String type) {
        return create.selectFrom(Tables.STREAM_TYPE).where(Tables.STREAM_TYPE.NAME.eq(type)).fetchOne();
    }

    public synchronized GameRecord createGame(Game game) {
        return createGame(Integer.parseInt(game.id), game.name, game.boxArtUrl);
    }

    public synchronized StreamTypeRecord createStreamType(String type) {
        StreamTypeRecord record = create.newRecord(Tables.STREAM_TYPE);
        record.setName(type);
        record.store();
        return record;
    }

    public synchronized UserTypeRecord getUserType(String type) {
        return create.selectFrom(Tables.USER_TYPE).where(Tables.USER_TYPE.TYPE.eq(type)).fetchOne();
    }

    public synchronized UserTypeRecord createUserType(String type) {
        UserTypeRecord userTypeRecord = create.newRecord(Tables.USER_TYPE);
        userTypeRecord.setType(type);
        userTypeRecord.store();
        return userTypeRecord;
    }

    public synchronized BroadcasterTypeRecord getBroadcasterType(String broadcasterType) {
        return create.selectFrom(Tables.BROADCASTER_TYPE).where(Tables.BROADCASTER_TYPE.TYPE.eq(broadcasterType)).fetchOne();
    }

    public synchronized BroadcasterTypeRecord createBroadcasterType(String broadcasterType) {
        BroadcasterTypeRecord record = create.newRecord(Tables.BROADCASTER_TYPE);
        record.setType(broadcasterType);
        record.store();
        return record;
    }

    public synchronized UserRecord createUser(twitch.explorer.scraper.twitchApi.json.users.User userJson, UserTypeRecord userType, BroadcasterTypeRecord broadcasterType) {
        UserRecord userRecord = create.newRecord(Tables.USER);
        userRecord.setBroadcasterTypeId(broadcasterType.getBroadcasterTypeId());
        userRecord.setDescription(userJson.description);
        userRecord.setLogin(userJson.login);
        userRecord.setName(userJson.displayName);
        userRecord.setOfflineImage(userJson.offlineImageUrl);
        userRecord.setProfileImage(userJson.profileImageUrl);
        userRecord.setTotalViews(userJson.viewCount);
        userRecord.setUserId(Long.parseLong(userJson.id));
        userRecord.setUserTypeId(userType.getUserTypeId());
        userRecord.setUpdated(new Timestamp(System.currentTimeMillis()));
        userRecord.store();
        return userRecord;
    }

    public synchronized StreamRecord getStream(String id) {
        return create.selectFrom(STREAM).where(STREAM.STREAM_ID.eq(Long.parseLong(id))).fetchOne();
    }

    private final DateTimeFormatter dtf = DateTimeFormatter.ISO_INSTANT;

    public synchronized StreamRecord createStream(GameRecord game, LanguageRecord language, StreamTypeRecord streamType, UserRecord user, twitch.explorer.scraper.twitchApi.json.stream.Stream stream) {
        StreamRecord record = create.newRecord(STREAM);
        record.setViewCount(stream.viewerCount);
        // record.setEnded();
        if (game != null)
            record.setGameId(game.getGameId());
        record.setLanguageId(language.getLanguageId());

        TemporalAccessor creationAccessor = dtf.parse(stream.startedAt);
        Timestamp timestamp = Timestamp.from(Instant.from(creationAccessor));

        record.setStarted(timestamp);
        record.setStreamId(Long.parseLong(stream.id));
        record.setStreamTypeId(streamType.getStreamTypeId());
        record.setThumbnail(stream.thumbnailUrl);
        record.setTitle(stream.title);
        record.setUserId(user.getUserId());
        record.store();
        return record;
    }

    public synchronized Result<Record1<Long>> getListContainedIn(Collection<Long> userId) {
        //create.insertInto(USER).values().execute();
        return create.select(USER.USER_ID).from(USER).where(USER.USER_ID.in(userId)).fetch();
    }

    public synchronized Result<Record1<Integer>> getExistingGamesByIds(HashSet<Integer> liveGameIds) {
        return create.select(GAME.GAME_ID).from(GAME).where(GAME.GAME_ID.in(liveGameIds)).fetch();
    }

    public synchronized Result<StreamRecord> getEndedStreams(HashSet<Long> streams) {
        return create.selectFrom(STREAM).where(STREAM.ENDED.isNull().and(STREAM.STREAM_ID.notIn(streams))).fetch();
    }

    public synchronized void getUsersThatAreLive() {
        // create.selectFrom(STREAM).where(STREAM.ENDED.isNull());
        create.select().from(STREAM).leftJoin(FOLLOWERS).using(STREAM.USER_ID).where(STREAM.ENDED.isNull());
    }

    public synchronized void createFollower(int amountOfFollowers, Long userId, Timestamp timestamp) {
        FollowersRecord record = create.newRecord(FOLLOWERS);
        record.setFetched(timestamp);
        record.setUserId(userId);
        record.setFollowers(amountOfFollowers);
        record.store();
    }

    public synchronized Result<LiveLongestTimeSinceFollowerUpdateViewRecord> getLongestTimeSinceFollowers() {
        return create.selectFrom(LIVE_LONGEST_TIME_SINCE_FOLLOWER_UPDATE_VIEW).fetch();
    }

    public void createFollowers(ArrayList<Follows> follows) {
        ArrayList<FollowersRecord> followersRecords = new ArrayList<>(follows.size());
        for (Follows follow : follows) {
            FollowersRecord followersRecord = new FollowersRecord();
            followersRecord.setFetched(new Timestamp(System.currentTimeMillis()));
            followersRecord.setUserId(follow.userID);
            followersRecord.setFollowers(follow.total);
            followersRecords.add(followersRecord);
        }
        synchronized (this) {
            create.batchInsert(followersRecords).execute();
        }
    }


    public void createVote(long userId, boolean isPositive, String sessionToken, String remoteHost) throws DataAccessException {
        VoteRecord voteRecord = new VoteRecord();
        voteRecord.setUserId(userId);
        VoteState voteState = isPositive ? VoteState.positive : VoteState.negative;
        voteRecord.setState(voteState);
        voteRecord.setCookie(sessionToken);
        create.executeInsert(voteRecord);
    }

    public synchronized Result<UserTypeRecord> getUserTypes() {
        return create.selectFrom(UserType.USER_TYPE).fetch();
    }

    public synchronized Result<GamesLiveRecord> getStreamedGames() {
      return create.selectFrom(GAMES_LIVE).fetch();
    }

    public Result<BroadcasterTypeRecord> getBroadcasterTypes() {
        return create.selectFrom(BROADCASTER_TYPE).fetch();
    }
}

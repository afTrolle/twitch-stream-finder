package twitch.explorer.database;


import org.jooq.*;
import org.jooq.impl.DSL;
import twitch.explorer.database.jooq.db.Tables;
import twitch.explorer.database.jooq.db.tables.records.*;
import twitch.explorer.scraper.json.games.Game;
import twitch.explorer.settings.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;

import static twitch.explorer.database.jooq.db.Tables.STREAM;
import static twitch.explorer.database.jooq.db.Tables.USER;


public class JooqHandler {

    //Jooq MySql Connection
    private Connection conn;
    private DSLContext create;

    public JooqHandler() throws SQLException {
        String userName = Config.getMySqlUserName();
        String password = Config.getMySqlPassword();
        String url = "jdbc:mysql://" + Config.getMySqlUrl();

        // Connection is the only JDBC resource that we need
        conn = DriverManager.getConnection(url, userName, password);
        create = DSL.using(conn, SQLDialect.MYSQL);
    }

    private void getGames() {
        Result<GameRecord> gameRecord = create.selectFrom(Tables.GAME).fetch();
        for (GameRecord r : gameRecord) {
            String awesome = r.getName();
        }
    }

    public GameRecord createGame(int gameId, String name, String artUrl) {
        GameRecord record = create.newRecord(Tables.GAME);
        record.setGameId(gameId);
        record.setName(name);
        record.setArtUrl(artUrl);
        record.store();
        return record;
    }

    public GameRecord getGameById(String gameId) {
        return create.selectFrom(Tables.GAME).where(Tables.GAME.GAME_ID.eq(Integer.parseInt(gameId))).fetchOne();
    }

    public boolean existsCommunity(String community) {
        return false;
    }

    public boolean existsStreamType(String streamType) {
        return false;
    }


    public UserRecord getUser(String userId) {
        return create.selectFrom(Tables.USER).where(Tables.USER.USER_ID.eq(Integer.parseInt(userId))).fetchOne();
    }

    public boolean existsUser(String userId) {
        return false;
    }

    public LanguageRecord getLanguage(String language) {
        return create.selectFrom(Tables.LANGUAGE).where(Tables.LANGUAGE.NAME.eq(language)).fetchOne();
    }

    public LanguageRecord createLanguage(String language) {
        LanguageRecord record = create.newRecord(Tables.LANGUAGE);
        record.setName(language);
        record.store();
        return record;
    }

    public StreamTypeRecord getStreamType(String type) {
        return create.selectFrom(Tables.STREAM_TYPE).where(Tables.STREAM_TYPE.NAME.eq(type)).fetchOne();
    }

    public GameRecord createGame(Game game) {
        return createGame(Integer.parseInt(game.id), game.name, game.boxArtUrl);
    }

    public StreamTypeRecord createStreamType(String type) {
        StreamTypeRecord record = create.newRecord(Tables.STREAM_TYPE);
        record.setName(type);
        record.store();
        return record;
    }

    public UserTypeRecord getUserType(String type) {
        return create.selectFrom(Tables.USER_TYPE).where(Tables.USER_TYPE.TYPE.eq(type)).fetchOne();
    }

    public UserTypeRecord createUserType(String type) {
        UserTypeRecord userTypeRecord = create.newRecord(Tables.USER_TYPE);
        userTypeRecord.setType(type);
        userTypeRecord.store();
        return userTypeRecord;
    }

    public BroadcasterTypeRecord getBroadcasterType(String broadcasterType) {
        return create.selectFrom(Tables.BROADCASTER_TYPE).where(Tables.BROADCASTER_TYPE.TYPE.eq(broadcasterType)).fetchOne();
    }

    public BroadcasterTypeRecord createBroadcasterType(String broadcasterType) {
        BroadcasterTypeRecord record = create.newRecord(Tables.BROADCASTER_TYPE);
        record.setType(broadcasterType);
        record.store();
        return record;
    }

    public UserRecord createUser(twitch.explorer.scraper.json.users.User userJson, UserTypeRecord userType, BroadcasterTypeRecord broadcasterType) {
        UserRecord userRecord = create.newRecord(Tables.USER);
        userRecord.setBroadcasterTypeId(broadcasterType.getBroadcasterTypeId());
        userRecord.setDescription(userJson.description);
        userRecord.setLogin(userJson.login);
        userRecord.setName(userJson.displayName);
        userRecord.setOfflineImage(userJson.offlineImageUrl);
        userRecord.setProfileImage(userJson.profileImageUrl);
        userRecord.setTotalViews(userJson.viewCount);
        userRecord.setUserId(Integer.parseInt(userJson.id));
        userRecord.setUserTypeId(userType.getUserTypeId());
        userRecord.store();
        return userRecord;
    }

    public StreamRecord getStream(String id) {
        return create.selectFrom(STREAM).where(STREAM.STREAM_ID.eq(Double.parseDouble(id))).fetchOne();
    }

    private final DateTimeFormatter dtf = DateTimeFormatter.ISO_INSTANT;

    public StreamRecord createStream(GameRecord game, LanguageRecord language, StreamTypeRecord streamType, UserRecord user, twitch.explorer.scraper.json.stream.Stream stream) {
        StreamRecord record = create.newRecord(STREAM);
        record.setViewCount(stream.viewerCount);
        // record.setEnded();
        if (game != null)
            record.setGameId(game.getGameId());
        record.setLanguageId(language.getLanguageId());

        TemporalAccessor creationAccessor = dtf.parse(stream.startedAt);
        Timestamp timestamp = Timestamp.from(Instant.from(creationAccessor));

        record.setStarted(timestamp);
        record.setStreamId(Double.parseDouble(stream.id));
        record.setStreamTypeId(streamType.getStreamTypeId());
        record.setThumbnail(stream.thumbnailUrl);
        record.setTitle(stream.title);
        record.setUserId(user.getUserId());
        record.store();
        return record;
    }

    public Result<Record1<Integer>> getListContainedIn(Collection<Integer> userId){
        return create.select(USER.USER_ID).from(USER).where(USER.USER_ID.in(userId)).fetch();
    }
}

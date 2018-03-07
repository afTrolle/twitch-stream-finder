package twitch.explorer.scraper;

import org.jooq.Record1;
import org.jooq.Result;
import twitch.explorer.database.JooqHandler;
import twitch.explorer.database.jooq.db.tables.records.*;
import twitch.explorer.scraper.json.games.Game;
import twitch.explorer.scraper.json.games.Games;
import twitch.explorer.scraper.json.stream.Stream;
import twitch.explorer.scraper.json.stream.Streams;
import twitch.explorer.scraper.json.users.User;
import twitch.explorer.scraper.json.users.Users;
import twitch.explorer.utils.Printer;

import java.sql.Timestamp;
import java.util.*;

public class TwitchScrapper {

    private final JooqHandler jooqHandler;
    private TwitchApiClient client;
    private boolean isScraping = true;

    public TwitchScrapper(JooqHandler jooqHandler) {
        this.jooqHandler = jooqHandler;
    }

    public void start() {

        client = new TwitchApiClient();
        isScraping = true;

        new Thread(this::loopFunction).start();
    }


    private void loopFunction() {
        try {
            while (isScraping) {
                HashMap<String, Stream> liveStreams = gatherAllLiveStreams();
                createMissingUsers(liveStreams);
                createMissingGames(liveStreams);
                createAndUpdateStream(liveStreams);
                detectEndedStreams(liveStreams);
                updateLiveUsers(liveStreams);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateLiveUsers(HashMap<String, Stream> liveStreams) {

    }

    private void createMissingGames(HashMap<String, Stream> liveStreams) {

        HashSet<Integer> liveGameIds = new HashSet<>();
        for (Stream stream : liveStreams.values()) {
            String gameId = stream.gameId;
            if (gameId != null && !gameId.isEmpty()) {
                liveGameIds.add(Integer.parseInt(stream.gameId));
            }
        }

        Result<Record1<Integer>> existingUserIds = jooqHandler.getExistingGamesByIds(liveGameIds);
        for (Record1<Integer> record : existingUserIds) {
            liveGameIds.remove(record.value1());
        }

        ArrayList<Integer> gameIds = new ArrayList<>(100);

        for (Integer gameId : liveGameIds) {
            gameIds.add(gameId);
            if (gameIds.size() == 100) {
                Games subSetGames = client.getGames(gameIds);
                createGames(subSetGames.data);
                gameIds.clear();
            }
        }

        if (gameIds.size() > 0) {
            Games subSetGames = client.getGames(gameIds);
            createGames(subSetGames.data);
        }
    }

    private void createGames(List<Game> data) {
        for (Game game : data) {
            jooqHandler.createGame(game);
        }
    }

    private void detectEndedStreams(HashMap<String, Stream> streamCollection) {
        Collection<Stream> streams = streamCollection.values();
        HashSet<Long> streamIds = new HashSet<>(streams.size());
        for (Stream stream : streams) {
            streamIds.add(Long.parseLong(stream.id));
        }
        Result<StreamRecord> streamRecords = jooqHandler.getEndedStreams(streamIds);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        for (StreamRecord streamRecord : streamRecords) {
            streamRecord.setEnded(timestamp);
            streamRecord.update();
        }
    }

    private void createAndUpdateStream(HashMap<String, Stream> streamCollection) throws InterruptedException {

        for (Stream stream : streamCollection.values()) {
            //  stream.gameId;
            GameRecord game = null;
            if (stream.gameId != null && !stream.gameId.isEmpty() && Integer.parseInt(stream.gameId) > 0) {
                if ((game = jooqHandler.getGameById(stream.gameId)) == null) {
                    // game = jooqHandler.createGame(client.getGame(stream.gameId));
                }
            }

            //  stream.communityIds;
            /* TODO Add community Support
            for (String community : stream.communityIds) {
                System.out.println(community);
                if (!jooqHandler.existsCommunity(community)) {

                }
            }*/

            //  stream.language;
            LanguageRecord lanugage;
            if ((lanugage = jooqHandler.getLanguage(stream.language)) == null) {
                lanugage = jooqHandler.createLanguage(stream.language);
            }

            //  stream.type;
            StreamTypeRecord streamType;
            if ((streamType = jooqHandler.getStreamType(stream.type)) == null) {
                streamType = jooqHandler.createStreamType(stream.type);
            }


            //  stream.userId;
            UserRecord user;
            if ((user = jooqHandler.getUser(stream.userId)) == null) {
                //should never be called done before create stream is called.
                //user = createUser(stream.userId);
            }

            //Check if stream exists or create it

            StreamRecord streamRecord;
            if ((streamRecord = jooqHandler.getStream(stream.id)) == null) {
                jooqHandler.createStream(game, lanugage, streamType, user, stream);
            } else {
                // update stream entry
                updateStream(streamRecord, game, lanugage, streamType, user, stream);
            }

        }
    }

    private void updateUser(UserRecord user, Stream stream, User userJson) {
        if (user.getUserId() == Integer.parseInt(userJson.id)) {

        }

        user.getUserTypeId();
        user.getBroadcasterTypeId();
        user.getDescription();
        user.getLogin();
        user.getName();
        user.getOfflineImage();
        user.getProfileImage();
        user.getTotalViews();
    }

    private void updateStream(StreamRecord streamRecord, GameRecord game, LanguageRecord lanugage, StreamTypeRecord streamType, UserRecord user, Stream stream) {

        if (streamRecord == null) {
            System.out.println("No Stream record in UpdateStream");
            return;
        }

        if (game != null && game.getGameId() > 0 && !game.getGameId().equals(streamRecord.getGameId()))
            streamRecord.setGameId(game.getGameId());
        if (!streamRecord.getLanguageId().equals(lanugage.getLanguageId()))
            streamRecord.setLanguageId(lanugage.getLanguageId());
        if (!streamRecord.getStreamTypeId().equals(streamType.getStreamTypeId()))
            streamRecord.setStreamTypeId(streamType.getStreamTypeId());
        if (!streamRecord.getTitle().equals(stream.title))
            streamRecord.setTitle(stream.title);
        if (!streamRecord.getThumbnail().equals(stream.thumbnailUrl))
            streamRecord.setThumbnail(stream.thumbnailUrl);
        if (streamRecord.getViewCount() != stream.viewerCount)
            streamRecord.setViewCount(stream.viewerCount);

        streamRecord.update();
    }

    private UserRecord createUser(User userJson) throws InterruptedException {

        UserTypeRecord userType;
        if ((userType = jooqHandler.getUserType(userJson.type)) == null) {
            userType = jooqHandler.createUserType(userJson.type);
        }

        BroadcasterTypeRecord broadcasterType;
        if ((broadcasterType = jooqHandler.getBroadcasterType(userJson.broadcasterType)) == null) {
            broadcasterType = jooqHandler.createBroadcasterType(userJson.broadcasterType);
        }

        return jooqHandler.createUser(userJson, userType, broadcasterType);
    }


    private HashMap<String, Stream> gatherAllLiveStreams() throws InterruptedException {
        //Assuming at-least 25k live streamers
        HashMap<String, Stream> streamHashMap = new HashMap<>(25000);
        String streamCursor = null;

        while (true) {
            Streams twitchStreams = client.getHundredStreamsByCursor(streamCursor);

            //duplicates can be received. so check before adding it to collection.
            for (Stream stream : twitchStreams.data) {
                streamHashMap.putIfAbsent(stream.id, stream);
            }

            Printer.setNumberOfStreamsIndex(streamHashMap.size());

            streamCursor = twitchStreams.pagination.cursor;
            if (streamCursor == null || streamCursor.isEmpty()) {
                return streamHashMap;
            }
        }
    }

    private void createMissingUsers(HashMap<String, Stream> streamCollection) throws InterruptedException {

        //get live streaming userIDs as integer
        HashSet<Long> liveUserIds = new HashSet<>(streamCollection.size());
        for (Stream stream : streamCollection.values()) {
            liveUserIds.add(Long.parseLong(stream.userId));
        }

        //get existing users from DB using live users list
        Result<Record1<Long>> existingUserIds = jooqHandler.getListContainedIn(liveUserIds);
        for (Record1<Long> record : existingUserIds) {
            //remove existing user
            liveUserIds.remove(record.value1());
        }


        ArrayList<Long> userIds = new ArrayList<>(100);

        for (Long userId : liveUserIds) {
            userIds.add(userId);
            if (userIds.size() == 100) {
                Users subSetJsonUsers = client.getUsers(userIds);
                createUsers(subSetJsonUsers.data);
                userIds.clear();
            }
        }

        if (userIds.size() > 0) {
            Users subSetJsonUsers = client.getUsers(userIds);
            createUsers(subSetJsonUsers.data);
        }
    }

    private void createUsers(List<User> jsonUsers) throws InterruptedException {
        for (User user : jsonUsers) {
            createUser(user);
        }
    }

}

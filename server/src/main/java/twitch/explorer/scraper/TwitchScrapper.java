package twitch.explorer.scraper;

import com.sun.jersey.core.util.MultivaluedMapImpl;
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
import twitch.explorer.settings.Config;

import java.util.ArrayList;
import java.util.List;

public class TwitchScrapper {

    private final JooqHandler jooqHandler;
    private TwitchApiClient client;
    private boolean isScraping = true;
    private Sleeper sleeper;

    public TwitchScrapper(JooqHandler jooqHandler) {
        this.jooqHandler = jooqHandler;
    }

    public void start() {
        String twitchApiClient = Config.getTwitchClientId();
        String twitchClientSecret = Config.getTwtichClientSecret();

        client = new TwitchApiClient(twitchApiClient, twitchClientSecret);
        isScraping = true;

        sleeper = new Sleeper();
        sleeper.setSleepInterval(!twitchClientSecret.isEmpty());

        new Thread(this::loopFunction).start();
    }


    public void stop() {
        isScraping = false;
    }


    private void loopFunction() {
        try {
            while (isScraping) {
                ArrayList<Stream> streamCollection = gatherAllStreams();
                createMissingUsers(streamCollection);
                createAndUpdateStream(streamCollection);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void createAndUpdateStream(ArrayList<Stream> streamCollection) throws InterruptedException {

        for (Stream stream : streamCollection) {
            //  stream.gameId;
            GameRecord game = null;
            if (stream.gameId != null && !stream.gameId.isEmpty() && Integer.parseInt(stream.gameId) > 0) {
                if ((game = jooqHandler.getGameById(stream.gameId)) == null) {
                    game = jooqHandler.createGame(getGame(stream.gameId));
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
                streamRecord = jooqHandler.createStream(game, lanugage, streamType, user, stream);
            } else {
                // update stream entry
                updateStream(streamRecord);
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

    private void updateStream(StreamRecord streamRecord) {

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

    private twitch.explorer.scraper.json.users.User getUser(String userID) throws InterruptedException {
        sleeper.sleep();
        Users users = client.getUsers(gameHeader(userID));
        return users.data.get(0);
    }

    private Game getGame(String gameId) throws InterruptedException {
        sleeper.sleep();
        Games games = client.getGames(gameHeader(gameId));
        if (games.data.size() == 0) {
            System.out.println("WTF");
            return null;
        }
        return games.data.get(0);
    }

    private MultivaluedMapImpl gameHeader(String gameId) {
        MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.add("id", gameId);
        return map;
    }

    int counter = 0;

    private ArrayList<Stream> gatherAllStreams() throws InterruptedException {
        ArrayList<Stream> streamsCollection = new ArrayList<>();
        ArrayList<Integer> missingUsers = new ArrayList<>();
        String streamCursor = null;

        while (true) {
            sleeper.sleep();
            Streams twitchStreams = client.getHundredStreamsByCursor(streamCursor);
            streamsCollection.addAll(twitchStreams.data);
            streamCursor = twitchStreams.pagination.cursor;

            if (counter == 4) {
                counter = 0;
                streamCursor = null;
                return streamsCollection;
            } else {
                counter++;
            }

            //TODO Remove
            if (streamCursor == null || streamCursor.isEmpty()) {
                return streamsCollection;
            }
        }
    }

    private void createMissingUsers(ArrayList<Stream> streamCollection) throws InterruptedException {

        //get users remove duplicates
        ArrayList<Integer> userCollection = new ArrayList<>(streamCollection.size());
        for (Stream stream : streamCollection) {
            if (!userCollection.contains(Integer.parseInt(stream.userId)))
                userCollection.add(Integer.parseInt(stream.userId));
        }

        //remove existing users
        Result<Record1<Integer>> listContainedIn = jooqHandler.getListContainedIn(userCollection);
        for (Record1<Integer> integerRecord1 : listContainedIn) {
            userCollection.remove(integerRecord1.value1());
        }

        while (userCollection.size() > 0) {
            ArrayList<Integer> userIds = new ArrayList<>();
            int numbersToFetch = userCollection.size() > 100 ? 100 : userCollection.size();

            for (int i = 0; i < numbersToFetch; i++) {
                userIds.add(userCollection.remove(0));
            }
            sleeper.sleep();
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

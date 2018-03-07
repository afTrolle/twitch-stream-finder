package twitch.explorer.scraper;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import twitch.explorer.scraper.json.games.Game;
import twitch.explorer.scraper.json.games.Games;
import twitch.explorer.scraper.json.stream.Streams;
import twitch.explorer.scraper.json.users.Users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * tools used can be found on how I use jersey
 * https://blogs.oracle.com/enterprisetechtips/consuming-restful-web-services-with-the-jersey-client-api
 */
public class TwitchApiClient {

    private final Gson gson;
    private final TwitchApiAuth apiAuth;

    private final WebResource gamesRes;
    private final WebResource streamsRes;
    private final WebResource usersRes;

    private final TwitchRateLimiter twitchRateLimiter;

    TwitchApiClient() {
        gson = new Gson();
        twitchRateLimiter = TwitchRateLimiter.get();

        Client client = Client.create();
        WebResource rootRes = client.resource("https://api.twitch.tv/helix/");

        // set api endpoints
        gamesRes = rootRes.path("games");
        streamsRes = rootRes.path("streams");
        usersRes = rootRes.path("users");

        // set print for jersey for debugging.
        // client.addFilter(new LoggingFilter(System.out));

        //Auth
        WebResource authRes = client.resource("https://id.twitch.tv/oauth2/token");
        apiAuth = new TwitchApiAuth(gson, authRes);
    }

    /**
     * Add id to header param
     */
    private MultivaluedMapImpl addIdHeader(String id) {
        MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.add("id", id);
        return map;
    }

    private MultivaluedMapImpl addIdsHeader(Collection userIds) {
        MultivaluedMapImpl map = new MultivaluedMapImpl();
        for (Object Id : userIds) {
            map.add("id", Id.toString());
        }
        return map;
    }

    /**
     * Perform get on webResource return with type of class
     */
    private <T> T get(WebResource resource, Class<T> classOfT) {
        WebResource.Builder webResource = apiAuth.auth(resource);
        twitchRateLimiter.sleep();
        String jsonResponse = webResource.get(String.class);
        return gson.fromJson(jsonResponse, classOfT);
    }

    private Streams getStreams(MultivaluedMapImpl queryParams) throws UniformInterfaceException {
        WebResource resource = (queryParams == null ? streamsRes : streamsRes.queryParams(queryParams));
        return get(resource, Streams.class);
    }

    private Users getUsers(MultivaluedMapImpl queryParams) throws UniformInterfaceException {
        WebResource resource = (queryParams == null ? usersRes : usersRes.queryParams(queryParams));
        return get(resource, Users.class);
    }


    public Games getGames(ArrayList<Integer> gameIds) {
        WebResource resource = gamesRes.queryParams(addIdsHeader(gameIds));
        return get(resource, Games.class);
    }

    public Game getGame(String gameId) {
        WebResource webResource = gamesRes.queryParams(addIdHeader(gameId));
        Games games = get(webResource, Games.class);
        if (games == null || games.data == null || 0 == games.data.size()) {
            return null;
        }
        return games.data.get(0);
    }

    public Users getUsers(ArrayList<Long> userIds) {
        MultivaluedMapImpl map = addIdsHeader(userIds);
        return getUsers(map);
    }

    public Streams getHundredStreamsByCursor(String streamCursor) {
        MultivaluedMapImpl map = new MultivaluedMapImpl();
        if (streamCursor != null && !streamCursor.isEmpty())
            map.add("after", streamCursor);
        map.add("first", "100");
        return getStreams(map);
    }


}

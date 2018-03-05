package twitch.explorer.scraper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import twitch.explorer.scraper.json.auth.Oauth;
import twitch.explorer.scraper.json.games.Games;
import twitch.explorer.scraper.json.stream.Streams;
import twitch.explorer.scraper.json.users.User;
import twitch.explorer.scraper.json.users.Users;

import java.util.ArrayList;

/**
 * tools used can be found on how I use jersey
 * https://blogs.oracle.com/enterprisetechtips/consuming-restful-web-services-with-the-jersey-client-api
 */
public class TwitchApiClient {

    private final Gson gson;

    private final String twitchClientKey;
    private final String twitchSecretKey;

    private Oauth authToken;

    private final WebResource gamesRes;
    private final WebResource streamsRes;
    private final WebResource usersRes;
    private final WebResource authRes;

    TwitchApiClient(String twitchClientKey, String twitchSecretKey) {
        gson = new Gson();

        this.twitchClientKey = twitchClientKey;
        this.twitchSecretKey = twitchSecretKey;

        Client client = Client.create();
        WebResource rootRes = client.resource("https://api.twitch.tv/helix/");

        //Auth
        authRes = client.resource("https://id.twitch.tv/oauth2/token");

        // set api endpoints
        gamesRes = rootRes.path("games");
        streamsRes = rootRes.path("streams");
        usersRes = rootRes.path("users");

        // set print for jersey for debugging.
        // client.addFilter(new LoggingFilter(System.out));

        updateToken();
    }


    /**
     * Gets Streams from twitch server
     * https://dev.twitch.tv/docs/api/reference#get-streams
     */
    public Streams getStreams(MultivaluedMapImpl queryParams) throws UniformInterfaceException {
        WebResource resource = (queryParams == null ? streamsRes : streamsRes.queryParams(queryParams));
        String jsonResponse = setHeaderAuth(resource).get(String.class);
        return gson.fromJson(jsonResponse, Streams.class);
    }

    /**
     * Gets Streams from twitch server
     * https://dev.twitch.tv/docs/api/reference#get-streams
     */
    public Streams getHundredStreamsByCursor(String streamCursor) {
        MultivaluedMapImpl map = new MultivaluedMapImpl();
        if (streamCursor != null && !streamCursor.isEmpty())
            map.add("after", streamCursor);
        map.add("first", "100");
        // map.add("type", "live");
        return getStreams(map);
    }


    /**
     * Gets Streams from twitch server
     * https://dev.twitch.tv/docs/api/reference#get-streams
     */
    public Users getUsers(MultivaluedMapImpl queryParams) throws UniformInterfaceException {
        WebResource resource = (queryParams == null ? usersRes : usersRes.queryParams(queryParams));
        String jsonResponse = setHeaderAuth(resource).get(String.class);
        return gson.fromJson(jsonResponse, Users.class);
    }

    /**
     * Gets Streams from twitch server
     * https://dev.twitch.tv/docs/api/reference#get-streams
     */
    public Games getGames(MultivaluedMapImpl queryParams) throws UniformInterfaceException {
        WebResource resource = (queryParams == null ? gamesRes : gamesRes.queryParams(queryParams));
        String jsonResponse = setHeaderAuth(resource).get(String.class);
        return gson.fromJson(jsonResponse, Games.class);
    }

    private WebResource.Builder setHeaderAuth(WebResource resource) {
        WebResource.Builder builder = resource.header("Client-ID", twitchClientKey);
        if (authToken != null) {
            builder = builder.header("Authorization", " Bearer " + authToken.accessToken);
        }
        return builder;
    }


    private Oauth getAuthToken() throws UniformInterfaceException {
        MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.add("client_id", twitchClientKey);
        map.add("client_secret", twitchSecretKey);
        map.add("grant_type", "client_credentials");
        String jsonResponse = authRes.queryParams(map).post(String.class);
        return gson.fromJson(jsonResponse, Oauth.class);
    }


    private void updateToken() {
        if (twitchSecretKey != null && !twitchSecretKey.isEmpty()) {
            if (authToken == null || authToken.isTokenExperied()) {
                authToken = getAuthToken();
            }
        }
    }

    public Users getUsers(ArrayList<Integer> userCollection) {
        MultivaluedMapImpl map = new MultivaluedMapImpl();
        for (Integer userId : userCollection) {
            map.add("id", userId.toString());
        }
        return getUsers(map);
    }




    /*
    private Oauth refershToken(Oauth oldAuth) throws UniformInterfaceException {
        MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.add("client_id", twitchClientKey);
        map.add("client_secret", twitchSecretKey);
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", oldAuth.accessToken);
        String jsonResponse = authRes.queryParams(map).post(String.class);
        return gson.fromJson(jsonResponse, Oauth.class);
    }
    */

}

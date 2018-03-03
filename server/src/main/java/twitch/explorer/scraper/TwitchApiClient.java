package twitch.explorer.scraper;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import twitch.explorer.scraper.json.auth.Oauth;
import twitch.explorer.scraper.json.games.Games;
import twitch.explorer.scraper.json.stream.Streams;
import twitch.explorer.scraper.json.users.Users;
import twitch.explorer.settings.Config;

/**
 * tools used can be found on how I use jersey
 * https://blogs.oracle.com/enterprisetechtips/consuming-restful-web-services-with-the-jersey-client-api
 */
public class TwitchApiClient {

    private Gson gson;
    private String authKey;
    private Client client;

    private WebResource gamesRes;
    private WebResource streamsRes;
    private WebResource usersRes;
    private WebResource authRes;

    public TwitchApiClient() {
        gson = new Gson();
        authKey = Config.getTwitchClientId();
        client = Client.create();
        WebResource rootRes = client.resource("https://api.twitch.tv/helix/");

        //Auth
        authRes = client.resource("https://id.twitch.tv/oauth2/token");

        // set api endpoints
        gamesRes = rootRes.path("games");
        streamsRes = rootRes.path("streams");
        usersRes = rootRes.path("users");

        // set print or not
        // client.addFilter(new LoggingFilter(System.out));
    }

    /**
     * Gets Streams from twitch server
     * https://dev.twitch.tv/docs/api/reference#get-streams
     */
    public Streams getStreams(MultivaluedMapImpl queryParams) throws UniformInterfaceException {
        WebResource resource = (queryParams == null ? streamsRes : streamsRes.queryParams(queryParams));
        String jsonResponse = setHeaderAuth(resource, authKey).get(String.class);
        return gson.fromJson(jsonResponse, Streams.class);
    }

    /**
     * Gets Streams from twitch server
     * https://dev.twitch.tv/docs/api/reference#get-streams
     */
    public Users getUsers(MultivaluedMapImpl queryParams) throws UniformInterfaceException {
        WebResource resource = (queryParams == null ? usersRes : usersRes.queryParams(queryParams));
        String jsonResponse = setHeaderAuth(resource, authKey).get(String.class);
        return gson.fromJson(jsonResponse, Users.class);
    }

    /**
     * Gets Streams from twitch server
     * https://dev.twitch.tv/docs/api/reference#get-streams
     */
    public Games getGames(MultivaluedMapImpl queryParams) throws UniformInterfaceException {
        WebResource resource = (queryParams == null ? gamesRes : gamesRes.queryParams(queryParams));
        String jsonResponse = setHeaderAuth(resource, authKey).get(String.class);
        return gson.fromJson(jsonResponse, Games.class);
    }

    private static WebResource.Builder setHeaderAuth(WebResource resource, String authKey) {
        return resource.header("Client-ID", authKey);
    }

    public Oauth getAuthToken(String clientSecret) {
        MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.add("client_id", authKey);
        map.add("client_secret", clientSecret);
        map.add("grant_type", "client_credentials");
        String jsonRepsone = authRes.queryParams(map).post(String.class);
        return gson.fromJson(jsonRepsone, Oauth.class);
    }

    public Oauth refershToken(String clientSecret, Oauth oldAuth) {
        MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.add("client_id", authKey);
        map.add("client_secret", clientSecret);
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", oldAuth.accessToken);
        String jsonRepsone = authRes.queryParams(map).post(String.class);
        return gson.fromJson(jsonRepsone, Oauth.class);
    }

}

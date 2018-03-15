package twitch.explorer.scraper.twitchApi;

import com.google.gson.Gson;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import twitch.explorer.scraper.twitchApi.json.auth.Oauth;
import twitch.explorer.settings.Config;

public class TwitchApiAuth {

    private final TwitchRateLimiter twitchRateLimiter;
    private final String twitchClientKey;
    private final String twitchSecretKey;
    private final WebResource authRes;
    private final Gson gson;

    private Oauth authToken;

    public TwitchApiAuth(Gson gson, WebResource authRes, TwitchApiConfig config, TwitchRateLimiter twitchRateLimiter) {
        this.gson = gson;
        this.twitchClientKey = config.clientId;
        this.twitchSecretKey = config.clientSecretId;
        this.twitchRateLimiter = twitchRateLimiter;
        this.authRes = authRes;
    }

    private Oauth getAuthToken() throws UniformInterfaceException {
        MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.add("client_id", twitchClientKey);
        map.add("client_secret", twitchSecretKey);
        map.add("grant_type", "client_credentials");
        twitchRateLimiter.sleep();
        String jsonResponse = authRes.queryParams(map).post(String.class);
        return gson.fromJson(jsonResponse, Oauth.class);
    }

    private void checkToken() {
        if (twitchSecretKey != null && !twitchSecretKey.isEmpty()) {
            if (authToken == null || authToken.isTokenExpired()) {
                authToken = getAuthToken();
            }
        }
    }

    /**
     * Append authentication to web request
     * if secret is given append it as bearer token
     *
     * @param resource
     * @return
     */
    public WebResource.Builder auth(WebResource resource) {
        WebResource.Builder builder = resource.header("Client-ID", twitchClientKey);
        checkToken();
        if (authToken != null) {
            builder = builder.header("Authorization", " Bearer " + authToken.accessToken);
        }

        return builder;
    }

}

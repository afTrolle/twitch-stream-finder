package twitch.explorer.scraper.twitchApi;

public class TwitchApiConfig {

    public final String clientId;
    public final String clientSecretId;
    public final int rateLimit;

    public TwitchApiConfig(String clientId, String clientSecretId, int rateLimit) {
        this.clientId = clientId;
        this.clientSecretId = clientSecretId;
        this.rateLimit = rateLimit;
    }
}

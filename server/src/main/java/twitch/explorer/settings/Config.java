package twitch.explorer.settings;

import java.util.ResourceBundle;

public class Config {

    private final ResourceBundle rb;
    private static Config config;

    public static Config get() {
        if (config == null)
            config = new Config();
        return config;
    }

    /**
     * Load twitchConfig.properties
     */
    private Config() {
        rb = ResourceBundle.getBundle("twitchConfig");
    }

    /**
     * get twitchClientID to be used by twitch rest api.
     *
     * @return returns string with api key
     */
    public String getTwitchClientId() {
        return rb.getString("twitchClientID");
    }

    /**
     * Get twitch api Client secret gives 4x pooling time
     *
     * @return returns string with twitch client secret code
     */
    public String getTwitchClientSecret() {
        return rb.getString("twitchClientSecret");
    }


    /**
     * get url of MySql
     *
     * @return string with url to  mySql Server
     */
    public String getMySqlUrl() {
        return rb.getString("MySqlUrl");
    }


    /**
     * get MySql username
     *
     * @return string with mySql Server UserName
     */
    public String getMySqlUserName() {
        return rb.getString("MySqlUserName");
    }

    /**
     * get MySql password
     *
     * @return string with mySql Server password
     */
    public String getMySqlPassword() {
        return rb.getString("MySqlPassword");
    }

    /**
     * get number of requests that can be made per minute to the twitch rest api
     *
     * @return integer api requests per minute
     */
    public int getTwitchRateLimit() {
        return Integer.parseInt(rb.getString("twitchRateLimit"));
    }

    /**
     * get how many minutes between live user updates.
     *
     * @return integer with how many minutes between updating live user data
     */
    public int getUpdateLiveUsersInterval() {
        return Integer.parseInt(rb.getString("twitchUpdateLiveUsersInterval"));
    }

    public int getTwitchFollowerRateLimit() {
        return Integer.parseInt(rb.getString("twitchFollowerLimit"));
    }

    public String getTwitchFollowerClientSecret() {
        return rb.getString("twitchFollowerSecret");
    }

    public String getTwitchFollowerClientId() {
        return rb.getString("twitchFollowerClientID");
    }

    public int getServerPort() {
        return Integer.parseInt(rb.getString("serverPort"));
    }
}

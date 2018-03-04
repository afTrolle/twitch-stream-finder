package twitch.explorer.settings;

import java.util.ResourceBundle;

public class Config {

    private static ResourceBundle rb;

    /**
     * Load twitchConfig.properties
     */
    public static void init() {
        rb = ResourceBundle.getBundle("twitchConfig");
    }

    /**
     * get twitchClientID to be used by twitch rest api.
     *
     * @return returns string with api key
     */
    public static String getTwitchClientId() {
        return rb.getString("twitchClientID");
    }

    /**
     * Get twitch api Client secret gives 4x pooling time
     * @return returns string with twitch client secret code
     */
    public static String getTwtichClientSecret() {
        return rb.getString("twitchClientSeceret");
    }


    /**
     * get url of MySql
     *
     * @return string with url to  mySql Server
     */
    public static String getMySqlUrl() {
        return rb.getString("MySqlUrl");
    }


    /**
     * get MySql username
     *
     * @return string with mySql Server UserName
     */
    public static String getMySqlUserName() {
        return rb.getString("MySqlUserName");
    }

    /**
     * get MySql password
     *
     * @return string with mySql Server password
     */
    public static String getMySqlPassword() {
        return rb.getString("MySqlPassword");
    }

}

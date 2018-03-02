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

}

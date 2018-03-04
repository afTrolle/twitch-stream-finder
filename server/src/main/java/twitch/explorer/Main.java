package twitch.explorer;


import twitch.explorer.database.JooqHandler;
import twitch.explorer.scraper.TwitchScrapper;
import twitch.explorer.settings.Config;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        Config.init();
        TwitchScrapper scrapper = new TwitchScrapper();
        scrapper.start();

        try {
            JooqHandler jooqHandler = new JooqHandler();

        //    jooqHandler.createGame("hello","http://helloworld.com");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

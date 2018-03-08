package twitch.explorer;


import twitch.explorer.database.JooqHandler;
import twitch.explorer.scraper.TwitchScrapper;
import twitch.explorer.settings.Config;

import java.sql.SQLException;
import java.sql.Timestamp;

public class Main {

    public static void main(String[] args) {
        try {
            JooqHandler jooqHandler = new JooqHandler();
            TwitchScrapper scrapper = new TwitchScrapper(jooqHandler);

       //     jooqHandler.createFollower(100, 186794521L, new Timestamp(System.currentTimeMillis()));
       //     jooqHandler.createFollower(110, 186794521L, new Timestamp(System.currentTimeMillis()+1000));
            scrapper.start();
            //  jooqHandler.createGame("hello","http://helloworld.com");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

package twitch.explorer;

import twitch.explorer.database.JooqHandler;
import twitch.explorer.restApi.RestServer;
import twitch.explorer.scraper.TwitchScrapper;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) {

        try {
            JooqHandler jooqHandler = JooqHandler.get();
            RestServer restServer = new RestServer();
            TwitchScrapper scrapper = new TwitchScrapper(jooqHandler);
            scrapper.start();
          //  sjooqHandler.createGame("hello", "http://helloworld.com");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

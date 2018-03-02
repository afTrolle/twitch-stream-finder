package twitch.explorer;


import twitch.explorer.scraper.TwitchScrapper;
import twitch.explorer.settings.Config;

public class Main {

    public static void main(String[] args) {
        Config.init();
        TwitchScrapper scrapper = new TwitchScrapper();
        scrapper.start();


    }


}

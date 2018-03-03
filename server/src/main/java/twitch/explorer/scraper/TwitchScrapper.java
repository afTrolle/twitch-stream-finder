package twitch.explorer.scraper;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import twitch.explorer.scraper.json.stream.Streams;

public class TwitchScrapper {

    private TwitchApiClient client;
    private boolean isScraping = true;
    private String streamPagnation = "";

    public void start() {
        client = new TwitchApiClient();
        isScraping = true;
        streamPagnation = "";

    //    new Thread(() -> loopFunction()).start();

    }

    public void stop() {
        isScraping = false;
    }

    private final int queriesPerMinAuthed = 120;
    private final int queriesPerMinute = 30;
    private final int millisPerMinute = 60 * 1000;
    private final double queriesPerMillis = (double) millisPerMinute / (double) queriesPerMinute;

    private void loopFunction() {
        while (isScraping) {
            sleep();
            MultivaluedMapImpl map = new MultivaluedMapImpl();
            if (!streamPagnation.isEmpty())
                map.add("after", streamPagnation);
            map.add("first", "100");
            map.add("type", "live");
            Streams stream = client.getStreams(map);
            streamPagnation = stream.pagination.cursor;
            if (stream.pagination.cursor.isEmpty()){
                isScraping = false;
            }
        }
    }


    private void sleep() {
        try {
            Thread.sleep((long) queriesPerMillis);
        } catch (InterruptedException e) {
            stop();
        }
    }
}

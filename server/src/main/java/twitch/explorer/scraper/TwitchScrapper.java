package twitch.explorer.scraper;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import twitch.explorer.scraper.json.stream.Streams;
import twitch.explorer.settings.Config;

public class TwitchScrapper {

    private TwitchApiClient client;
    private boolean isScraping = true;
    private String streamPagnation = "";

    private double timeBetweenQueriesMillis;


    public void start() {
        String twitchApiClient = Config.getTwitchClientId();
        String twitchClientSecret = Config.getTwtichClientSecret();

        client = new TwitchApiClient(twitchApiClient, twitchClientSecret);
        isScraping = true;
        streamPagnation = "";

        setQueryInterval(!twitchClientSecret.isEmpty());

       // client.getStreams(null);
            new Thread(() -> loopFunction()).start();
    }

    private void setQueryInterval(boolean isAuthenticated) {
        final double millisPerMinute = 60 * 1000;
        if (isAuthenticated) {
            timeBetweenQueriesMillis = millisPerMinute / 120D;
        } else {
            timeBetweenQueriesMillis = millisPerMinute / 30D;
        }
    }

    public void stop() {
        isScraping = false;
    }


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
            numOfStreams += stream.data.size();
            if (stream.pagination.cursor.isEmpty()) {
                isScraping = false;
            }
            printStreams();
        }
    }

    private int numOfStreams = 0;

    private void printStreams(){
       // if (numOfStreams % 1000  == 0)
            System.out.println("num Streams: "+numOfStreams);
    }

    private Long previousSleep;

    private void sleep() {

        try {


            Long currentTimeMillis = System.currentTimeMillis();
            if (previousSleep == null){
                previousSleep = currentTimeMillis;
            }
            Long elapsedTime =  (currentTimeMillis-previousSleep);
            if (elapsedTime > timeBetweenQueriesMillis){
                previousSleep = System.currentTimeMillis();
                return;
            }
            Long timeToSleep = (long) (timeBetweenQueriesMillis - elapsedTime);
            Thread.sleep(timeToSleep);
            previousSleep = System.currentTimeMillis();
        } catch (InterruptedException e) {
            stop();
        }
    }
}

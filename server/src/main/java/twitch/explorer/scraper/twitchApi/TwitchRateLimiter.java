package twitch.explorer.scraper.twitchApi;


public class TwitchRateLimiter {

    private Long previousSleep;
    private final double timeBetweenQueriesMillis;

    TwitchRateLimiter(TwitchApiConfig config) {
        final double millisPerMinute = 60 * 1000;
        timeBetweenQueriesMillis = millisPerMinute / (double) config.rateLimit;
    }

    public void sleep() {
        final Long currentTimeMillis = System.currentTimeMillis();
        if (previousSleep == null) {
            previousSleep = currentTimeMillis;
        }
        final Long elapsedTime = (currentTimeMillis - previousSleep);
        if (elapsedTime > timeBetweenQueriesMillis) {
            previousSleep = System.currentTimeMillis();
            return;
        }
        final Long timeToSleep = (long) (timeBetweenQueriesMillis - elapsedTime);
        try {
            Thread.sleep(timeToSleep);
        } catch (InterruptedException ignored) {
        }
        previousSleep = System.currentTimeMillis();
    }

}

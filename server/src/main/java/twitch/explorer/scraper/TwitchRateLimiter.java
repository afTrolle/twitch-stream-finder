package twitch.explorer.scraper;

import twitch.explorer.settings.Config;

public class TwitchRateLimiter {

    private static TwitchRateLimiter twitchRateLimiter;
    private Long previousSleep;
    private final double timeBetweenQueriesMillis;

    public static TwitchRateLimiter get() {
        if (twitchRateLimiter == null) {
            twitchRateLimiter = new TwitchRateLimiter();
        }
        return twitchRateLimiter;
    }

    private TwitchRateLimiter() {
        final double millisPerMinute = 60 * 1000;
        final Config config = Config.get();
        final int numberOfQueriesPerMin = config.getTwitchRateLimit();
        timeBetweenQueriesMillis = millisPerMinute / numberOfQueriesPerMin;
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

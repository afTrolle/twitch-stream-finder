package twitch.explorer.scraper;

public class Sleeper {

    private Long previousSleep;
    private double timeBetweenQueriesMillis;

    public void sleep() throws InterruptedException {
        Long currentTimeMillis = System.currentTimeMillis();
        if (previousSleep == null) {
            previousSleep = currentTimeMillis;
        }
        Long elapsedTime = (currentTimeMillis - previousSleep);
        if (elapsedTime > timeBetweenQueriesMillis) {
            previousSleep = System.currentTimeMillis();
            return;
        }
        Long timeToSleep = (long) (timeBetweenQueriesMillis - elapsedTime);
        Thread.sleep(timeToSleep);
        previousSleep = System.currentTimeMillis();
    }

    public void setSleepInterval(boolean isAuthenticated) {
        final double millisPerMinute = 60 * 1000;
        if (isAuthenticated) {
            timeBetweenQueriesMillis = millisPerMinute / 120D;
        } else {
            timeBetweenQueriesMillis = millisPerMinute / 30D;
        }
    }
}

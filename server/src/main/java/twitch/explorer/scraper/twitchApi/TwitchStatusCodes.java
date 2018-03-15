package twitch.explorer.scraper.twitchApi;

public class TwitchStatusCodes {

    // Request Not Valid. Something is wrong with the request.
    public final static int notValid = 400;
    //	Unauthorized. The OAuth token does not have the correct scope or does not have the required permission on behalf of the specified user.
    public final static int Unauthorized = 401;
    // Not Found. For example, the channel, user, or relationship could not be found.
    public final static int NotFound = 404;
    //	Too Many Requests
    public final static int TooMany = 429;
    // Internal Server Error 500
    public final static int InternalError = 500;
    // Service Unavailable. For example, the status of a game or ingest server cannot be retrieved.
    public final static int ServiceUnavailable = 503;

}

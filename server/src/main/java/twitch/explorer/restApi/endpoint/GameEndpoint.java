package twitch.explorer.restApi.endpoint;

import org.jooq.Result;
import twitch.explorer.database.JooqHandler;
import twitch.explorer.database.jooq.gen.tables.pojos.GamesLive;
import twitch.explorer.database.jooq.gen.tables.records.GamesLiveRecord;
import twitch.explorer.utils.GsonHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/game")
public class GameEndpoint {

    @CookieParam("JSESSIONID")
    private String token;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld() {

        try {
            JooqHandler jooqHandler = JooqHandler.get();
            List<GamesLive> streamedGames = jooqHandler.getStreamedGames();
            return GsonHelper.gson.toJson(streamedGames);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }

}

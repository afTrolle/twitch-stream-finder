package twitch.explorer.restApi.endpoint;

import org.jooq.Result;
import twitch.explorer.database.JooqHandler;
import twitch.explorer.database.jooq.gen.tables.records.GamesLiveRecord;
import twitch.explorer.utils.GsonHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/game")
public class GameEndpoint {

    @CookieParam("JSESSIONID")
    private String token;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld() {

        try {
            JooqHandler jooqHandler = JooqHandler.get();
            Result<GamesLiveRecord> streamedGames = jooqHandler.getStreamedGames();
            ArrayList<games> ret = new ArrayList<>();
            for (GamesLiveRecord streamedGame : streamedGames) {
                ret.add(new games(streamedGame.getArtUrl(), streamedGame.getGameId(), streamedGame.getName()));
            }
            return GsonHelper.gson.toJson(ret);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }

    public class games {

        public final String artUrl;
        public final Integer gameId;
        public final String name;

        public games(String artUrl, Integer gameId, String name) {
            this.artUrl = artUrl;
            this.gameId = gameId;
            this.name = name;
        }
    }


}

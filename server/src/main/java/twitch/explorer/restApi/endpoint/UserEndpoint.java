package twitch.explorer.restApi.endpoint;

import twitch.explorer.database.JooqHandler;
import twitch.explorer.database.jooq.gen.tables.pojos.BroadcasterType;
import twitch.explorer.database.jooq.gen.tables.pojos.UserInfoView;
import twitch.explorer.database.jooq.gen.tables.pojos.UserType;
import twitch.explorer.utils.GsonHelper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/user")
public class UserEndpoint {


    @GET
    @Path("userTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld() {

        List<UserType> userTypes = null;
        try {
            JooqHandler jooqHandler = JooqHandler.get();
            userTypes = jooqHandler.getUserTypes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GsonHelper.gson.toJson(userTypes);
    }

    @GET
    @Path("broadCasterTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public String broadCasterTypes() {
        List<BroadcasterType> broadcasterTypes = null;
        try {
            JooqHandler jooqHandler = JooqHandler.get();
            broadcasterTypes = jooqHandler.getBroadcasterTypes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GsonHelper.gson.toJson(broadcasterTypes);
    }

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld(@PathParam("userId") long userId) {

        UserInfoView userTypes = null;
        try {
            JooqHandler jooqHandler = JooqHandler.get();
            userTypes = jooqHandler.getUserData(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GsonHelper.gson.toJson(userTypes);
    }

}

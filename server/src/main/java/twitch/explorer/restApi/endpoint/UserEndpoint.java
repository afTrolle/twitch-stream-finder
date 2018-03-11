package twitch.explorer.restApi.endpoint;

import org.jooq.Result;
import twitch.explorer.database.JooqHandler;
import twitch.explorer.database.jooq.gen.tables.records.BroadcasterTypeRecord;
import twitch.explorer.database.jooq.gen.tables.records.UserTypeRecord;
import twitch.explorer.utils.GsonHelper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/user")
public class UserEndpoint {


    @GET
    @Path("userTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld() {

        ArrayList<String> userTypesStr = new ArrayList<>();
        try {
            JooqHandler jooqHandler = JooqHandler.get();
            Result<UserTypeRecord> userTypes = jooqHandler.getUserTypes();
            for (UserTypeRecord userType : userTypes) {
                userTypesStr.add(userType.getType());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //this is a new object everytime!
        //it's ru
        return GsonHelper.gson.toJson(userTypesStr);
    }

    @GET
    @Path("broadCasterTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public String broadCasterTypes() {

        ArrayList<String> userTypesStr = new ArrayList<>();
        try {
            JooqHandler jooqHandler = JooqHandler.get();
            Result<BroadcasterTypeRecord> broadcasterTypes = jooqHandler.getBroadcasterTypes();
            for (BroadcasterTypeRecord userType : broadcasterTypes) {
                userTypesStr.add(userType.getType());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //it's ru
        return GsonHelper.gson.toJson(userTypesStr);
    }
    
}

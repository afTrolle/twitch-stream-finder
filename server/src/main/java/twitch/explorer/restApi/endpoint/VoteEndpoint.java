package twitch.explorer.restApi.endpoint;


import org.jooq.exception.DataAccessException;
import twitch.explorer.database.JooqHandler;
import twitch.explorer.restApi.websocket.WebSocketHandler;
import twitch.explorer.restApi.websocket.response.UpdateObject;
import twitch.explorer.utils.GsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/vote")
public class VoteEndpoint {

    @POST
    @Path("/user/{userId}")
    public Response voteOnUser(@PathParam("userId") String userId, @QueryParam("positive") Boolean isPositive, @Context HttpServletRequest req) {
        HttpSession session = req.getSession(true);

        //session cookie
        String sessionToken = session.getId();
        //remote ip or proxy ip
        String remoteHost = req.getRemoteHost();

        if (isPositive == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(GsonHelper.gson.toJson("requires 'positive' query parameter to be true or false")).type(MediaType.APPLICATION_JSON).build();
        }

        long userIdL = 0;
        try {
            userIdL = Long.parseLong(userId);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(GsonHelper.gson.toJson("userID needs to be user a number")).type(MediaType.APPLICATION_JSON).build();
        }

        try {
            JooqHandler jooq = JooqHandler.get();
            jooq.createVote(userIdL, isPositive, sessionToken, remoteHost);
            UpdateObject updateObject = new UpdateObject(userIdL);
            WebSocketHandler.getInstance().sendUpdateToEveryone(updateObject);
        } catch (DataAccessException e) {
            if (e.getMessage().contains("cookie_UNIQUE")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(GsonHelper.gson.toJson("cookie already used")).type(MediaType.APPLICATION_JSON).build();
            } else if (e.getMessage().contains("CONSTRAINT `fk_vote_user` FOREIGN KEY")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(GsonHelper.gson.toJson("userID does not exist")).type(MediaType.APPLICATION_JSON).build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getCause().getMessage()).type(MediaType.APPLICATION_JSON).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }


        return Response.ok(GsonHelper.gson.toJson("created vote"), MediaType.APPLICATION_JSON).build();
    }
}

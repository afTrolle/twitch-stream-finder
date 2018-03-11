package twitch.explorer.restApi.endpoint;


import org.jooq.exception.DataAccessException;
import twitch.explorer.database.JooqHandler;
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
    public Response voteOnUser(@PathParam("userId") String userId, @QueryParam("positive") boolean isPositive, @Context HttpServletRequest req) {
        HttpSession session = req.getSession(true);

        //session cookie
        String sessionToken = session.getId();
        //remote ip or proxy ip
        String remoteHost = req.getRemoteHost();

        long userIdL = 0;
        try {
            userIdL = Long.parseLong(userId);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("userID needs to be user a number").build();
        }

        try {
            JooqHandler jooq = JooqHandler.get();
            jooq.createVote(userIdL, isPositive, sessionToken, remoteHost);
        } catch (DataAccessException e) {
            if (e.getMessage().contains("cookie_UNIQUE")) {
                return Response.status(Response.Status.BAD_REQUEST).entity("cookie already used").build();
            } else if (e.getMessage().contains("CONSTRAINT `fk_vote_user` FOREIGN KEY")) {
                return Response.status(Response.Status.BAD_REQUEST).entity("userID does not exist").build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getCause().getMessage()).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }

        return Response.ok(GsonHelper.gson.toJson("created vote"), MediaType.APPLICATION_JSON).build();
    }
}

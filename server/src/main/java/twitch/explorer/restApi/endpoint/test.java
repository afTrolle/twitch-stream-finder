package twitch.explorer.restApi.endpoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/entry-point")
public class test {


    @CookieParam("JSESSIONID")
    private String token;

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld(@Context HttpServletRequest req, @Context HttpServletResponse resp) {
        req.getSession(true);
        //this is a new object everytime!
        //it's ru
        return "object: "+this.toString()+ " thread: " +Thread.currentThread().getId() + " cookie: "+token;
    }

    @GET
    @Path("user")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld(@QueryParam("userID") Integer user) {
        //this is a new object everytime!
        //it's ru
        return "object: "+this.toString()+ " thread: " +Thread.currentThread().getId();
    }
}

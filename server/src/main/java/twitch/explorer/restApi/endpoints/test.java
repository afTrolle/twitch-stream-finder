package twitch.explorer.restApi.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/entry-point")
public class test {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        //this is a new object everytime!
        //it's ru
        return "object: "+this.toString()+ " thread: " +Thread.currentThread().getId();
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

package twitch.explorer.restApi.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("/stream")
public class StreamEndpoint {


    @Path("/search")
    @GET
    public String workwork(@Context UriInfo info) {

        MultivaluedMap<String, String> queryParameters = info.getQueryParameters();
        String minViewCount = queryParameters.getFirst("minViewCount");
        String maxViewCount = queryParameters.getFirst("maxViewCount");

        return queryParameters.toString();
    }
}

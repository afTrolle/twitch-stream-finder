package twitch.explorer.restApi.endpoint;

import com.google.gson.Gson;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;
import twitch.explorer.database.JooqHandler;
import twitch.explorer.database.jooq.gen.tables.pojos.LiveStreamUserVoteView;
import twitch.explorer.database.jooq.gen.tables.records.LiveStreamUserVoteViewRecord;
import twitch.explorer.utils.GsonHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static twitch.explorer.database.jooq.gen.Tables.LIVE_STREAM_USER_VOTE_VIEW;

@Path("/stream")
public class StreamEndpoint {


    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String searchStream(
            //stream params
            @QueryParam("streamTitle") String streamTitle,
            @DefaultValue("-1") @QueryParam("minViewCount") int minViewCount,
            @DefaultValue("-1") @QueryParam("maxViewCount") int maxViewCount,
            @DefaultValue("-1") @QueryParam("gameId") int gameId,
            @DefaultValue("-1") @QueryParam("languageId") int languageId,
            @DefaultValue("-1") @QueryParam("streamTypeId") int streamTypeId,
            @DefaultValue("-1") @QueryParam("minStreamTime") int minStreamTime,
            @DefaultValue("-1") @QueryParam("maxStreamTime") int maxStreamTime,
            //user params
            @QueryParam("userName") String userName,
            @QueryParam("userDescription") String userDescription,
            @DefaultValue("-1") @QueryParam("userTypeId") int userTypeId,
            @DefaultValue("-1") @QueryParam("minFollowerCount") int minFollowerCount,
            @DefaultValue("-1") @QueryParam("maxFollowerCount") int maxFollowerCount,
            @DefaultValue("-1") @QueryParam("minVoteRatio") double minVoteRatio,
            @DefaultValue("-1") @QueryParam("maxVoteRatio") double maxVoteRatio,
            @DefaultValue("-1") @QueryParam("minTotalViewCount") long minTotalViewCount,
            @DefaultValue("-1") @QueryParam("maxTotalViewCount") long maxTotalViewCount) {


        final ArrayList<Condition> conditions = new ArrayList<>();
        final twitch.explorer.database.jooq.gen.tables.LiveStreamUserVoteView view = LIVE_STREAM_USER_VOTE_VIEW;

        //stream params

        if (streamTitle != null && !streamTitle.isEmpty()) {
            Condition condition = DSL.condition("MATCH(live_stream_user_vote_view.title) AGAINST(?)", streamTitle);
            conditions.add(condition);
        }
        if (minViewCount >= 0) {
            conditions.add(view.VIEW_COUNT.greaterOrEqual(minViewCount));
        }
        if (maxViewCount >= 0) {
            conditions.add(view.VIEW_COUNT.lessOrEqual(maxViewCount));
        }
        if (gameId == 0) {
            conditions.add(LIVE_STREAM_USER_VOTE_VIEW.GAME_ID.isNull());
        } else if (gameId > 0) {
            conditions.add(LIVE_STREAM_USER_VOTE_VIEW.GAME_ID.eq(gameId));
        }
        if (languageId >= 0) {
            conditions.add(LIVE_STREAM_USER_VOTE_VIEW.LANGUAGE_ID.eq(languageId));
        }

        if (streamTypeId >= 0) {
            conditions.add(LIVE_STREAM_USER_VOTE_VIEW.STREAM_TYPE_ID.eq(streamTypeId));
        }

        //user params

        if (userName != null && !userName.isEmpty()) {
            Condition condition = DSL.condition("soundex(live_stream_user_vote_view.name) like CONCAT(\"%\",  soundex'?), \"%\")", userName);
            conditions.add(condition);
        }

        if (userDescription != null && !userDescription.isEmpty()) {
            Condition condition = DSL.condition("MATCH(live_stream_user_vote_view.description) AGAINST(?)", userName);
            conditions.add(condition);
        }

        if (userTypeId >= 0) {
            conditions.add(view.USER_TYPE_ID.eq(userTypeId));
        }

        if (minStreamTime >= 0) {
            final Timestamp timestamp = new Timestamp(System.currentTimeMillis() - minStreamTime);
            conditions.add(view.STARTED.greaterOrEqual(timestamp));
        }

        if (maxStreamTime >= 0) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis() - maxStreamTime);
            conditions.add(view.STARTED.lessOrEqual(timestamp));
        }

        if (minFollowerCount >= 0) {
            conditions.add(view.FOLLOWERS.greaterOrEqual(minFollowerCount));
        }

        if (maxFollowerCount >= 0) {
            conditions.add(view.FOLLOWERS.lessOrEqual(maxFollowerCount));
        }

        if (minVoteRatio >= 0) {
            conditions.add(view.RATIO.greaterOrEqual(new BigDecimal(minVoteRatio)));
        }

        if (maxVoteRatio >= 0) {
            conditions.add(view.RATIO.lessOrEqual(new BigDecimal(maxVoteRatio)));
        }

        if (minTotalViewCount >= 0) {
            conditions.add(view.TOTAL_VIEWS.greaterOrEqual(minTotalViewCount));
        }

        if (maxTotalViewCount >= 0) {
            conditions.add(view.TOTAL_VIEWS.lessOrEqual(minTotalViewCount));
        }

        try {
            JooqHandler jooqHandler = JooqHandler.get();
            List<LiveStreamUserVoteView> liveStreams = jooqHandler.searchStream(conditions);
            return GsonHelper.gson.toJson(liveStreams);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new WebApplicationException();
    }


}

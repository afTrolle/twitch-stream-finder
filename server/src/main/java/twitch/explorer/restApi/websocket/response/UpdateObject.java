package twitch.explorer.restApi.websocket.response;

import org.jooq.Result;
import twitch.explorer.database.jooq.gen.tables.records.StreamRecord;
import twitch.explorer.scraper.twitchApi.json.follower.Follows;

import java.util.ArrayList;
import java.util.List;

public class UpdateObject {

    List<Long> userIds;

    List<Long> streamIds;

    public UpdateObject(ArrayList<Follows> follows) {
        userIds = new ArrayList<>();
        for (Follows follow : follows) {
            userIds.add(follow.userID);
        }
    }

    public UpdateObject(StreamRecord streamRecord) {
        streamIds = new ArrayList<>();
        streamIds.add(streamRecord.getStreamId());
    }

    public UpdateObject(Result<StreamRecord> streamRecords) {
        streamIds = new ArrayList<>();
        for (StreamRecord streamRecord : streamRecords) {
            streamIds.add(streamRecord.getStreamId());
        }
    }

    public UpdateObject(long userId) {
        userIds = new ArrayList<>();
        userIds.add(userId);
    }
}

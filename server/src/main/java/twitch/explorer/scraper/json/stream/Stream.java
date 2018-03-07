
package twitch.explorer.scraper.json.stream;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stream {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("game_id")
    @Expose
    public String gameId;
    @SerializedName("community_ids")
    @Expose
    public List<String> communityIds = new ArrayList<String>();
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("viewer_count")
    @Expose
    public int viewerCount;
    @SerializedName("started_at")
    @Expose
    public String startedAt;
    @SerializedName("language")
    @Expose
    public String language;
    @SerializedName("thumbnail_url")
    @Expose
    public String thumbnailUrl;


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Stream) {
            return id.equals(((Stream) obj).id);
        }
        return super.equals(obj);
    }
}

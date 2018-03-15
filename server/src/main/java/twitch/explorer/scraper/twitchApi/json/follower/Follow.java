
package twitch.explorer.scraper.twitchApi.json.follower;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Follow {

    @SerializedName("from_id")
    @Expose
    public String fromId;
    @SerializedName("to_id")
    @Expose
    public String toId;
    @SerializedName("followed_at")
    @Expose
    public String followedAt;

}

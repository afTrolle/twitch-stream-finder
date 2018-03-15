
package twitch.explorer.scraper.twitchApi.json.follower;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import twitch.explorer.scraper.twitchApi.json.stream.Pagination;

public class Follows {

    @SerializedName("total")
    @Expose
    public int total;
    @SerializedName("data")
    @Expose
    public List<Follow> data = new ArrayList<Follow>();
    @SerializedName("pagination")
    @Expose
    public Pagination pagination;

    public Long userID;

}

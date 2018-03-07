
package twitch.explorer.scraper.twitchApi.json.stream;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("cursor")
    @Expose
    public String cursor;

}

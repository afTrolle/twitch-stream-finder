
package twitch.explorer.scraper.json.games;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("box_art_url")
    @Expose
    public String boxArtUrl;

}

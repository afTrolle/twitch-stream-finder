
package twitch.explorer.scraper.json.games;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import twitch.explorer.scraper.json.games.Game;

public class Games {

    @SerializedName("data")
    @Expose
    public List<Game> data = new ArrayList<Game>();

}

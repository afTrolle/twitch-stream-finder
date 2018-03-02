
package twitch.explorer.scraper.json.stream;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Streams {

    @SerializedName("data")
    @Expose
    public List<Stream> data = new ArrayList<Stream>();
    @SerializedName("pagination")
    @Expose
    public Pagination pagination;

}

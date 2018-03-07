
package twitch.explorer.scraper.twitchApi.json.users;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("data")
    @Expose
    public List<User> data = new ArrayList<User>();

}

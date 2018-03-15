
package twitch.explorer.scraper.twitchApi.json.users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("display_name")
    @Expose
    public String displayName;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("broadcaster_type")
    @Expose
    public String broadcasterType;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("profile_image_url")
    @Expose
    public String profileImageUrl;
    @SerializedName("offline_image_url")
    @Expose
    public String offlineImageUrl;
    @SerializedName("view_count")
    @Expose
    public long viewCount;

}

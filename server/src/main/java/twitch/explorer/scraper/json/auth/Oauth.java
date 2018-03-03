
package twitch.explorer.scraper.json.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Oauth {

    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("expires_in")
    @Expose
    public int expiresIn;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;

}

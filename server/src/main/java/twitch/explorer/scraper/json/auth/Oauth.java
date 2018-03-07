
package twitch.explorer.scraper.json.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Oauth {

    @SerializedName("access_token")
    @Expose
    public String accessToken;

    /**
     * number of seconds until the token expires
     */
    @SerializedName("expires_in")
    @Expose
    public int expiresIn;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;

    private final long authTokenCreatedMillis = System.currentTimeMillis();

    public boolean isTokenExpired() {
        final long currentMillis = System.currentTimeMillis();
        //Calculate number of seconds run and add 1 for some error of margin.
        final long elapsedSec = ((currentMillis - authTokenCreatedMillis) / 1000) + 1;
        return elapsedSec >= expiresIn;
    }


}

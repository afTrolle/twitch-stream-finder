package com.twitchexplorer.twitchexplorer.lib.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * Created by alexa on 2018-03-15.
 */

public class TwitchHelper {

    public static boolean isTwitchInstalled(Context context) {
        return isPackageInstalled("tv.twitch.android.app", context);
    }

    public static Intent start(Context context, String userId) {
        // brings user to the market if app is not installed already
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("twitch://stream/" + userId));
        return intent;
    }

    private static boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}

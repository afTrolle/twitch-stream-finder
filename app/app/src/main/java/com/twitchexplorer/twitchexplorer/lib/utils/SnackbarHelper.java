package com.twitchexplorer.twitchexplorer.lib.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackbarHelper {

    public static void showWarning(View layout, String message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_SHORT).show();
    }

}

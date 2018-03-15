package com.twitchexplorer.twitchexplorer.lib.utils;

import android.text.Editable;
import android.text.TextWatcher;

public class TextIgnoreWatcher implements TextWatcher {
    private String previous;
    private boolean appendOld;
    private boolean clear;
    boolean cleared;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (!appendOld)
            previous = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        appendOld = !previous.equals(s.toString());
        cleared = s.length() == 0;
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (clear) {
            clear = false;
            s.append(previous);
            return;
        }
        if (cleared) {
            previous = "";
            clear = false;
            return;
        }
        if (appendOld) {
            clear = true;
            s.clear();
        }
    }

}

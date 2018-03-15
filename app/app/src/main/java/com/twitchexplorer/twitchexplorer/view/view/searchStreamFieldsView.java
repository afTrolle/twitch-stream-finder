package com.twitchexplorer.twitchexplorer.view.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.twitchexplorer.twitchexplorer.R;

public class searchStreamFieldsView extends BaseView {

    public searchStreamFieldsView(@NonNull Context context) {
        super(context);
    }

    public searchStreamFieldsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public searchStreamFieldsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public searchStreamFieldsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int getViewInflateLayout() {
        return R.layout.search_stream_layout;
    }


}

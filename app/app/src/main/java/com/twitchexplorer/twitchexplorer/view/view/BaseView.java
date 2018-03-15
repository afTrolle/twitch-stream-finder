package com.twitchexplorer.twitchexplorer.view.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseView extends FrameLayout {

    private Unbinder unbinder;

    public abstract @LayoutRes
    int getViewInflateLayout();

    public BaseView(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyle) {
        LayoutInflater.from(context).inflate(getViewInflateLayout(), this, true);
        unbinder = ButterKnife.bind(this, this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
    }
}

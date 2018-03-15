package com.twitchexplorer.twitchexplorer.view.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ImageViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.twitchexplorer.twitchexplorer.R;
import com.twitchexplorer.twitchexplorer.model.pojo.LiveStreamUserVoteView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StreamView extends BaseView {

    @BindView(R.id.stream_preview_image_view)
    ImageView imageView;

    public StreamView(ViewGroup parent) {
        super(parent.getContext());
        LayoutInflater.from(parent.getContext()).inflate(getViewInflateLayout(), this, true);
    }

    @Override
    public int getViewInflateLayout() {
        return R.layout.stream_layout;
    }

    public StreamView(@NonNull Context context) {
        super(context);
    }

    public StreamView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StreamView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StreamView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.unbinder = ButterKnife.bind(this, this);
    }


    @Override
    protected void init(Context context, AttributeSet attrs, int defStyle) {

    }

    public void setData(LiveStreamUserVoteView view) {
        String thumbUrl = view.getThumbnail().replace("{width}", "480").replace("{height}", "270");
        if (imageView != null)
            Picasso.get().load(thumbUrl).into(imageView);
    }
}

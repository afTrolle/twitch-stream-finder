package com.twitchexplorer.twitchexplorer.view.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twitchexplorer.twitchexplorer.R;
import com.twitchexplorer.twitchexplorer.model.pojo.LiveStreamUserVoteView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StreamsAdapter extends RecyclerView.Adapter<StreamsAdapter.ViewHolder> {

    private final List<LiveStreamUserVoteView> viewData;
    private final OnClickListener onClickListener;

    public boolean isStreamId(Long streamId) {
        for (LiveStreamUserVoteView viewDatum : viewData) {
            if (viewDatum.getStreamId().equals(streamId)) {
                return true;
            }
        }
        return false;
    }

    public interface OnClickListener {
        public void onClick(LiveStreamUserVoteView viewData);
    }

    public StreamsAdapter(List<LiveStreamUserVoteView> viewData, OnClickListener onClickListener) {
        this.viewData = viewData;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // StreamView streamView = new StreamView(parent);
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.stream_layout, parent, false);
        final ViewHolder vh = new ViewHolder(inflate);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(vh.viewData);
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(viewData.get(position));
    }

    @Override
    public int getItemCount() {
        if (viewData == null || viewData.size() == 0)
            return 0;
        return viewData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View itemView;
        //  public final View itemView;

        @BindView(R.id.stream_preview_image_view)
        ImageView imageView;
        @BindView(R.id.stream_title_text)
        TextView titleTextView;
        @BindView(R.id.stream_user_name_text)
        TextView userTextView;
        private LiveStreamUserVoteView viewData;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            //    this.itemView = itemView;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void setData(LiveStreamUserVoteView viewData) {
            this.viewData = viewData;
            String thumbUrl = viewData.getThumbnail().replace("{width}", "480").replace("{height}", "270");
            if (imageView != null)
                Picasso.get().load(thumbUrl).into(imageView);
            titleTextView.setText(viewData.getTitle());
            userTextView.setText(viewData.getName());
            itemView.invalidate();
        }
    }
}

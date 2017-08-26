package com.vsay.demo.mvpdatabinding.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vsay.demo.mvpdatabinding.R;
import com.vsay.demo.mvpdatabinding.databinding.FeedItemBinding;
import com.vsay.demo.mvpdatabinding.listener.EventHandler;
import com.vsay.demo.mvpdatabinding.models.Event;

import java.util.List;

/**
 * Created by vsaya on 2/11/17.
 */
public class EventFeedAdapter extends RecyclerView.Adapter<EventFeedAdapter.FeedViewHolder> {
    private List<Event> events;
    private Context mContext;
    private EventHandler mEventHandler;

    public EventFeedAdapter(Context context, List<Event> events) {
        this.mContext = context;
        this.events = events;
        this.mEventHandler = new EventHandler(mContext);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FeedItemBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.feed_item, parent, false);
        return new FeedViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(final FeedViewHolder holder, int position) {
        holder.feedItemBinding.setEvent(events.get(position));
        holder.feedItemBinding.setEventHandler(mEventHandler);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void clearData() {
        events.clear();
    }

    public void addAllData(List<Event> posts) {
        this.events.addAll(posts);
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {
        private FeedItemBinding feedItemBinding;

        FeedViewHolder(FeedItemBinding itemView) {
            super(itemView.getRoot());
            feedItemBinding = itemView;
        }
    }
}
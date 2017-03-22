package com.shekhar.app.ipl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.feeds.RssItem;
import com.shekhar.app.ipl.view.NewsListItemViewHolder;

import java.util.ArrayList;

/**
 * Created by shekhar on 17/03/17.
 */

public class NewFeedListAdapter extends RecyclerView.Adapter<NewsListItemViewHolder> {

    private Context mContext;
    private ArrayList<RssItem> visitorTables;

    public NewFeedListAdapter(Context mContext, ArrayList<RssItem> visitorTables) {
        this.mContext = mContext;
        this.visitorTables = visitorTables;

    }

    @Override
    public NewsListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item_row_news_feed, parent, false);
        return new NewsListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsListItemViewHolder holder, int position) {
        RssItem team = visitorTables.get(position);

        holder.title.setText(team.getTitle());
        holder.description.setText(team.getDescription());
        holder.date.setText(team.getPubDate());
    }

    @Override
    public int getItemCount() {
        return visitorTables.size();
    }
}

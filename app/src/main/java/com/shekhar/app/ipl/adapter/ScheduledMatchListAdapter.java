package com.shekhar.app.ipl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.model.ScheduledMatch;
import com.shekhar.app.ipl.view.ScheduledMatchListItemViewHolder;

import java.util.ArrayList;

/**
 * Created by shekhar on 17/03/17.
 */

public class ScheduledMatchListAdapter extends RecyclerView.Adapter<ScheduledMatchListItemViewHolder> {

    private Context mContext;
    private ArrayList<ScheduledMatch> visitorTables;

    public ScheduledMatchListAdapter(Context mContext, ArrayList<ScheduledMatch> visitorTables) {
        this.mContext = mContext;
        this.visitorTables = visitorTables;
    }

    @Override
    public ScheduledMatchListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item_row_scheduled_match, parent, false);
        return new ScheduledMatchListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ScheduledMatchListItemViewHolder holder, int position) {
        ScheduledMatch scheduledMatch = visitorTables.get(position);

        holder.matchBetween.setText(scheduledMatch.getMatch());
        holder.matchVenue.setText(scheduledMatch.getVanue());
        holder.scheduleDate.setText(scheduledMatch.getDate() + " " + scheduledMatch.getTime());

    }

    @Override
    public int getItemCount() {
        return visitorTables.size();
    }
}

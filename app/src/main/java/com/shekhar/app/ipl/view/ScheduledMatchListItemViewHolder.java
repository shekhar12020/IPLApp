package com.shekhar.app.ipl.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shekhar.app.ipl.R;

/**
 * Created by shekhar on 17/03/2017.
 */

public class ScheduledMatchListItemViewHolder extends RecyclerView.ViewHolder {
    public TextView matchVenue;
    public TextView scheduleDate;
    public TextView matchBetween;

    public ScheduledMatchListItemViewHolder(View itemView) {
        super(itemView);
        matchVenue = (TextView) itemView.findViewById(R.id.matchVenue);
        scheduleDate = (TextView) itemView.findViewById(R.id.scheduleDate);
        matchBetween = (TextView) itemView.findViewById(R.id.matchBetween);
    }
}

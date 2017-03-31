package com.shekhar.app.ipl.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shekhar.app.ipl.R;

/**
 * Created by shekhar on 17/03/2017.
 */

public class ScheduledMatchListItemViewHolder extends RecyclerView.ViewHolder {
    public TextView matchVenue;
    public TextView scheduleDate;
    public TextView daysRemaining;
    public TextView matchBetween;

    public ImageView team1Img;
    public ImageView team2Img;

    public ScheduledMatchListItemViewHolder(View itemView) {
        super(itemView);
        matchVenue = (TextView) itemView.findViewById(R.id.matchVenue);
        scheduleDate = (TextView) itemView.findViewById(R.id.scheduleDate);
        daysRemaining = (TextView) itemView.findViewById(R.id.daysRemaining);
        matchBetween = (TextView) itemView.findViewById(R.id.matchBetween);

        team1Img = (ImageView) itemView.findViewById(R.id.team1Img);
        team2Img = (ImageView) itemView.findViewById(R.id.team2Img);

    }
}

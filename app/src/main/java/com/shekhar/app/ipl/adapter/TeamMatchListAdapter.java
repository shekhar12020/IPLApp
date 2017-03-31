package com.shekhar.app.ipl.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ParseException;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.model.match.ScheduledMatch;
import com.shekhar.app.ipl.util.DebugLog;
import com.shekhar.app.ipl.view.ScheduledMatchListItemViewHolder;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by shekhar on 17/03/17.
 */

public class TeamMatchListAdapter extends RecyclerView.Adapter<ScheduledMatchListItemViewHolder> {

    private Context mContext;
    private ArrayList<ScheduledMatch> visitorTables;

    public TeamMatchListAdapter(Context mContext, ArrayList<ScheduledMatch> visitorTables) {
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
        holder.scheduleDate.setText(scheduledMatch.getDate().trim() + " " + scheduledMatch.getTime().trim());

        Picasso.with(mContext)
                .load(scheduledMatch.getTeam1Img())
                .placeholder(R.drawable.ipl_img_placeholder)
                .error(R.drawable.ipl_img_placeholder)
                .into(holder.team1Img);

        Picasso.with(mContext)
                .load(scheduledMatch.getTeam2Img())
                .placeholder(R.drawable.ipl_img_placeholder)
                .error(R.drawable.ipl_img_placeholder)
                .into(holder.team2Img);

        if (scheduledMatch.getStatus().equalsIgnoreCase("To be played")) {
            holder.daysRemaining.setText(daysRemaining(dateFormat(scheduledMatch.getDate().trim())) + " days to go");
            holder.daysRemaining.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
        } else if (scheduledMatch.getStatus().equalsIgnoreCase("Completed")) {
            holder.daysRemaining.setText(scheduledMatch.getWinner() + " Won");
            holder.daysRemaining.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
        } else if (scheduledMatch.getStatus().equalsIgnoreCase("Started")) {
            holder.daysRemaining.setText("LIVE");
            holder.daysRemaining.setTypeface(Typeface.DEFAULT, Typeface.BOLD_ITALIC);
        }
    }

    @Override
    public int getItemCount() {
        return visitorTables.size();
    }

    private int daysRemaining(String scheduleDate) {

        int diffInDays = 0;
        SimpleDateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date d;
        Date d1;
        try {
            d1 = dfDate.parse(scheduleDate);
            d = dfDate.parse(getCurrentDate("dd/MM/yyyy"));
            diffInDays = (int) ((d1.getTime() - d.getTime()) / (1000 * 60 * 60 * 24));
            DebugLog.d("diffInDays : " + diffInDays);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return diffInDays;
    }

    public static String dateFormat(String dateInput) {
        String str2 = null;

        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy", Locale.US);
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Date date1;

        try {
            date1 = formatter.parse(dateInput);
            str2 = (formatter1.format(date1));
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }

        return str2;
    }

    private String getCurrentDate(String outputFormat) {

        String timezoneName = TimeZone.getDefault().getDisplayName();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(outputFormat, Locale.getDefault());
        df.setTimeZone(TimeZone.getTimeZone(timezoneName));
        String formattedDate = df.format(c.getTime());

        return formattedDate;
    }
}

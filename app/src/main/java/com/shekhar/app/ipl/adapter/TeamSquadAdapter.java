package com.shekhar.app.ipl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.model.team.Squad;
import com.shekhar.app.ipl.view.TeamListItemViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shekhar on 29/03/17.
 */

public class TeamSquadAdapter extends RecyclerView.Adapter<TeamListItemViewHolder> {

    private Context mContext;
    private ArrayList<Squad> visitorTables;

    public TeamSquadAdapter(Context mContext, ArrayList<Squad> visitorTables) {
        this.mContext = mContext;
        this.visitorTables = visitorTables;

    }

    @Override
    public TeamListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item_row_team, parent, false);
        return new TeamListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TeamListItemViewHolder holder, int position) {
        final Squad squad = visitorTables.get(position);

        holder.teamName.setText(squad.getpName());
        holder.teamOwner.setText(squad.getpType() + " (" + squad.getCountry() + ")");
        holder.teamCaptain.setText("Bating : " + squad.getBats());
        holder.extra.setText("Bowling : " + squad.getBowls());

        if (!squad.getpImg().equalsIgnoreCase("")) {
            Picasso.with(mContext)
                    .load(squad.getpImg())
                    .placeholder(R.drawable.loading_border)
                    .error(R.drawable.loading_border)
                    .into(holder.teamPhoto);
        } else {
            Picasso.with(mContext)
                    .load(R.drawable.loading_border)
                    .placeholder(R.drawable.loading_border)
                    .error(R.drawable.loading_border)
                    .into(holder.teamPhoto);
        }
    }

    @Override
    public int getItemCount() {
        return visitorTables.size();
    }
}

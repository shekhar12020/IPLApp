package com.shekhar.app.ipl.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.activity.TeamDetailsActivity;
import com.shekhar.app.ipl.model.team.Team;
import com.shekhar.app.ipl.view.TeamListItemViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shekhar on 17/03/17.
 */

public class TeamListAdapter extends RecyclerView.Adapter<TeamListItemViewHolder> {

    private Context mContext;
    private ArrayList<Team> visitorTables;

    public TeamListAdapter(Context mContext, ArrayList<Team> visitorTables) {
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
        final Team team = visitorTables.get(position);

        holder.teamName.setText(team.getTeam_name());
        holder.teamOwner.setText(team.getOwner());
        holder.teamCaptain.setText(team.getCaptain() + " (Captain)");
        holder.extra.setText(team.getHome_ground());

        if (!team.getImage_url().equalsIgnoreCase("")) {
            Picasso.with(mContext)
                    .load(team.getImage_url())
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

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TeamDetailsActivity.class);
                intent.putExtra("teamDetails", team);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return visitorTables.size();
    }
}

package com.shekhar.app.ipl.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shekhar.app.ipl.R;

/**
 * Created by shekhar on 17/03/2017.
 */

public class TeamListItemViewHolder extends RecyclerView.ViewHolder {
    public TextView teamName;
    public TextView teamOwner;
    public TextView teamCaptain;
    public ImageView teamPhoto;

    public TeamListItemViewHolder(View itemView) {
        super(itemView);
        teamName = (TextView) itemView.findViewById(R.id.teamName);
        teamOwner = (TextView) itemView.findViewById(R.id.teamOwner);
        teamCaptain = (TextView) itemView.findViewById(R.id.teamCaptain);
        teamPhoto = (ImageView) itemView.findViewById(R.id.teamPhoto);
    }
}

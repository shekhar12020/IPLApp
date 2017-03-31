package com.shekhar.app.ipl.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shekhar.app.ipl.R;

/**
 * Created by shekhar on 17/03/2017.
 */

public class TeamListItemViewHolder extends RecyclerView.ViewHolder {

    public TextView teamName;
    public TextView teamOwner;
    public TextView teamCaptain;
    public TextView extra;
    public ImageView teamPhoto;
    public RelativeLayout parentLayout;

    public TeamListItemViewHolder(View itemView) {
        super(itemView);
        teamName = (TextView) itemView.findViewById(R.id.teamName);
        teamOwner = (TextView) itemView.findViewById(R.id.teamOwner);
        teamCaptain = (TextView) itemView.findViewById(R.id.teamCaptain);
        extra = (TextView) itemView.findViewById(R.id.extra);
        teamPhoto = (ImageView) itemView.findViewById(R.id.teamPhoto);

        parentLayout = (RelativeLayout) itemView.findViewById(R.id.parentLayout);
    }
}

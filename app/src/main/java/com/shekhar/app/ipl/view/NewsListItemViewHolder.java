package com.shekhar.app.ipl.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shekhar.app.ipl.R;

/**
 * Created by shekhar on 17/03/2017.
 */

public class NewsListItemViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView description;
    public TextView date;
    public RelativeLayout parentLayout;

    public NewsListItemViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        parentLayout = (RelativeLayout) itemView.findViewById(R.id.parentLayout);
        date = (TextView) itemView.findViewById(R.id.date);
    }
}

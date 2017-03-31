package com.shekhar.app.ipl.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.adapter.NewFeedListAdapter;
import com.shekhar.app.ipl.feeds.RssItem;
import com.shekhar.app.ipl.feeds.RssReader;

import java.util.ArrayList;

public class NewFeedsActivity extends BaseActivity {

    private RelativeLayout parentLayout;
    private RecyclerView newsFeedList;

    private String finalUrl = "http://www.espncricinfo.com/rss/content/story/feeds/1078425.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feeds);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("IPL Updates");

        bindViews();
    }

    private void bindViews() {
        parentLayout = (RelativeLayout) findViewById(R.id.parentLayout);
        newsFeedList = (RecyclerView) findViewById(R.id.newsFeedList);

        new DownloadNewsTask().execute(finalUrl);
    }

    @Override
    protected int getStatusBarColor() {
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                supportFinishAfterTransition();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class DownloadNewsTask extends AsyncTask<String, Void, ArrayList<RssItem>> {

        protected ArrayList<RssItem> doInBackground(String... urls) {
            ArrayList<RssItem> RssItems = new ArrayList<>();
            try {
                RssReader rssReader = new RssReader(finalUrl);
                RssItems = rssReader.getItems();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return RssItems;
        }

        protected void onPostExecute(ArrayList<RssItem> result) {
            if (result != null && result.size() > 0) {
                LinearLayoutManager llm = new LinearLayoutManager(NewFeedsActivity.this);
                newsFeedList.setLayoutManager(llm);
                newsFeedList.setAdapter(new NewFeedListAdapter(NewFeedsActivity.this, result));
            }
        }
    }
}

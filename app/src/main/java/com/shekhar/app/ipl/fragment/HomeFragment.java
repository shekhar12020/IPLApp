package com.shekhar.app.ipl.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.shekhar.app.ipl.R;
import com.shekhar.app.ipl.adapter.NewFeedListAdapter;
import com.shekhar.app.ipl.feeds.RssItem;
import com.shekhar.app.ipl.feeds.RssReader;
import com.shekhar.app.ipl.global.GlobalData;
import com.shekhar.app.ipl.util.DebugLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by shekhar on 17/13/17.
 */

public class HomeFragment extends BaseFragment implements GoogleApiClient.OnConnectionFailedListener {

    private View rootView;
    private RelativeLayout parentLayout;
    private RecyclerView newsFeedList;
    private CardView newsLabelCard;

    private SignInButton btnGoogleSignIn;
    private TextView btnMakePrediction;
    private TextView authenticationMessage;
    private TextView status;

    private GoogleApiClient mGoogleApiClient;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final int RC_SIGN_IN = 9001;

    private String finalUrl = "http://www.espncricinfo.com/rss/content/story/feeds/1078425.xml";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindViews();

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                updateUI(user);
            }
        };

        new DownloadNewsTask().execute(finalUrl);
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
                newsLabelCard.setVisibility(View.VISIBLE);
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                newsFeedList.setLayoutManager(llm);
                newsFeedList.setAdapter(new NewFeedListAdapter(getActivity(), result));
                newsLabelCard.setVisibility(View.VISIBLE);
            } else {
                newsLabelCard.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void bindViews() {
        parentLayout = (RelativeLayout) rootView.findViewById(R.id.parentLayout);

        btnMakePrediction = (TextView) rootView.findViewById(R.id.btnMakePrediction);
        authenticationMessage = (TextView) rootView.findViewById(R.id.authenticationMessage);
        status = (TextView) rootView.findViewById(R.id.status);
        newsLabelCard = (CardView) rootView.findViewById(R.id.newsLabelCard);

        TextView daysRemaining = (TextView) rootView.findViewById(R.id.daysRemaining);
        daysRemaining.setText(daysRemaining(dateFormat("April 5, 2017")) + " Days Remaining");

        btnGoogleSignIn = (SignInButton) rootView.findViewById(R.id.btnGoogleSignIn);
        newsFeedList = (RecyclerView) rootView.findViewById(R.id.newsFeedList);

        btnGoogleSignIn.setSize(SignInButton.SIZE_WIDE);
        btnGoogleSignIn.setColorScheme(SignInButton.COLOR_DARK);

        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).registerReceiver(updateStatus, new IntentFilter("updateStatus"));
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).unregisterReceiver(updateStatus);
        super.onDestroy();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        mAuth.signOut();

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        dismissActivitySpinner();

        if (user != null) {

            SharedPreferences UserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor preferencesEditor = UserInfo.edit();

            preferencesEditor.putBoolean("isLogin", true);
            preferencesEditor.putString("id", user.getUid());
            preferencesEditor.putString("email", user.getEmail());
            preferencesEditor.putString("username", user.getDisplayName());
            preferencesEditor.putString("photoUrl", user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : "");

            preferencesEditor.apply();

            GlobalData.getInstance().setUserId(user.getUid());
            GlobalData.getInstance().setProfileEmail(user.getEmail());
            GlobalData.getInstance().setProfileName(user.getDisplayName());
            GlobalData.getInstance().setProfilePhoto(user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : "");

            DebugLog.d(user.getUid());
            DebugLog.d(user.getEmail());
            DebugLog.d(user.getPhotoUrl().toString());

            btnGoogleSignIn.setVisibility(View.GONE);
            authenticationMessage.setVisibility(View.GONE);
            btnMakePrediction.setVisibility(View.VISIBLE);

        } else {
            btnGoogleSignIn.setVisibility(View.VISIBLE);
            authenticationMessage.setVisibility(View.VISIBLE);
            btnMakePrediction.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                updateUI(null);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(getActivity(), "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    private BroadcastReceiver updateStatus = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getExtras() != null) {
                setStatus(intent);
            }
        }
    };

    private void setStatus(Intent intent) {
        boolean statusDisplay = intent.getBooleanExtra("status_display", false);
        String statusText = intent.getStringExtra("status_text");

        if (statusDisplay) {
            status.setVisibility(View.VISIBLE);
            status.setText(statusText);
        } else {
            status.setVisibility(View.GONE);
            status.setText("");
        }
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

        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy", Locale.US);
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

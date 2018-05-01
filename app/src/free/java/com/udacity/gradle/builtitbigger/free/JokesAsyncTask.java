package com.udacity.gradle.builtitbigger.free;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import it.redlor.androidjokelibrary.JokeActivity;

/*Differently than paid flavor, Interstitial ad is displayed
*
*
* */

public class JokesAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi mApi = null;
    ProgressBar mProgressBar;
    private Context mContext;
    private String mResult;
    private InterstitialAd mInterstitialAd;

    public JokesAsyncTask(Context context, ProgressBar progressBar) {
        this.mContext = context;
        this.mProgressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (mApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            mApi = builder.build();
        }
        try {
            return mApi.provideJoke().execute().getJokeText();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mResult = result;

        // Interstitial Ad
        mInterstitialAd = new InterstitialAd(mContext);
        mInterstitialAd.setAdUnitId(mContext.getResources().getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
                mInterstitialAd.show();

            }

            @Override
            public void onAdClosed() {
                startJokeActivity();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
                startJokeActivity();
            }
        });

        AdRequest adRequest = new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void startJokeActivity() {
        Intent intent = new Intent(mContext, JokeActivity.class);
        intent.putExtra("passedJoke", mResult);
        System.out.println("passing:" + mResult);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

}
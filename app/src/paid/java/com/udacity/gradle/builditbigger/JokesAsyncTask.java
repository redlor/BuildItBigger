package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import it.redlor.androidjokelibrary.JokeActivity;

public class JokesAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi mApi = null;
    private Context mContext;
    private String mResult;
    private ProgressBar mProgressBar;

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
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }

        mResult = result;
        startJokeActivity();
    }


    private void startJokeActivity() {
        Intent intent = new Intent(mContext, JokeActivity.class);
        intent.putExtra("passedJoke", mResult);
        System.out.println("passing:" + mResult);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

}
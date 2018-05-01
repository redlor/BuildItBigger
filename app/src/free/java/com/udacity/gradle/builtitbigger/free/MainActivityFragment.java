package com.udacity.gradle.builtitbigger.free;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.redlor.javalibrary.JavaJokes;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    @BindView(R.id.joke_button)
    Button jokeButton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        // JavaJokes
        final JavaJokes javaJoke = new JavaJokes();

        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    Intent intent = new Intent(getContext(), JokeActivity.class);
                String joke = javaJoke.getRandomJoke();
                intent.putExtra("passedJoke", joke);
                startActivity(intent);*/

                new JokesAsyncTask(getContext(), progressBar).execute();
            }
        });


        return root;
    }
}

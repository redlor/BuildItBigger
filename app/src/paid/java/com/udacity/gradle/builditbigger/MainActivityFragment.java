package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

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

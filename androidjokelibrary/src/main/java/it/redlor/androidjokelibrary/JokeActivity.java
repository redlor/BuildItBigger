package it.redlor.androidjokelibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public String joke;

   TextView jokeTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        jokeTv = findViewById(R.id.joke_text_view);
        joke = getIntent().getStringExtra("passedJoke");
        jokeTv.setText(joke);


    }


}

package edu.sti.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private String score1;
    private String score2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score1 = extras.getString("curScore");
            score2 = extras.getString("overScore");
            ((TextView)findViewById(R.id.score1)).setText(score1);
            ((TextView)findViewById(R.id.score2)).setText(score2);
        }
    }
}

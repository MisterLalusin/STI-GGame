package edu.sti.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class leaderboard extends AppCompatActivity {

    DbHelper dbHelper;
    private DbHelper myDb;
    private String name;
    private String score;
    private TextView lead;
    private String value = "";
    private boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        myDb = new DbHelper(this);
        lead = (TextView)findViewById(R.id.txtlead);


        Cursor res = myDb.getLeaderBoard();
        if (res.getCount() != 0) {

        }
        while (res.moveToNext()) {
            name = res.getString(1) + " "+ res.getString(2) +" "+ res.getString(3);
            score = res.getString(7);

            value = value.replace("null", "") + name + "                     " + score + "\n\n";

            lead.setText(value);
        }
    }
}

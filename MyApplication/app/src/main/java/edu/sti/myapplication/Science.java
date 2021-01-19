package edu.sti.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Science extends AppCompatActivity {
    Integer points,temppoints;
    Button a,b,c,d;
    TextView question,score;
    DbHelper dbHelper;
    String uname;

    SharedPreferences sharedPreferences;
    public static  final String mypreference ="mypref";
    private String scoreFromDatabase;
    private Dialog mDialog;
    private Button mDialogyes, mDialogno;
    private TextView score1, score2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);
        a = findViewById(R.id.btna);
        b = findViewById(R.id.btnb);
        c = findViewById(R.id.btnc);
        d = findViewById(R.id.btnd);
        score = findViewById(R.id.txtscore);
        question = findViewById(R.id.quest);
        points = 0;
        score.setText(points.toString());

        dbHelper = new DbHelper(this);

        question1();

        getUserData();

        try {
            getScore();
        }
        catch (Exception ex) {
            Toast.makeText(this, ""+ex, Toast.LENGTH_SHORT).show();
        }

    }

    private void getScore() {
        Cursor res = dbHelper.getUserData(uname);
        if (res.getCount() == 0) {

        }
        while (res.moveToNext()) {
            String myScore = res.getString(7);
            Toast.makeText(getApplicationContext(), ""+myScore, Toast.LENGTH_SHORT).show();
            scoreFromDatabase = myScore;
        }
    }


    public void getUserData() {
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.contains("unameKey")){
            uname = sharedPreferences.getString("unameKey", "");
            Toast.makeText(getApplicationContext(),""+uname,Toast.LENGTH_SHORT).show();
        }
    }

    public void saveScore() {
        try {

            String newPoints = (Integer.parseInt(scoreFromDatabase) + points)+"";

            dbHelper.updatePoints(uname,newPoints);
        }
        catch (Exception ex) {
            Toast.makeText(this, ""+ex, Toast.LENGTH_SHORT).show();
        }
    }


    public void question1(){

        question.setText("QUESTION 1");
        a.setBackgroundColor(Color.parseColor("#ffffff"));
        b.setBackgroundColor(Color.parseColor("#ffffff"));
        c.setBackgroundColor(Color.parseColor("#ffffff"));
        d.setBackgroundColor(Color.parseColor("#ffffff"));
        a.setText("A");
        b.setText("B");
        c.setText("C");
        d.setText("D");

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.setBackgroundColor(Color.parseColor("#006000"));//CORRECT ANSWER
                b.setBackgroundColor(Color.parseColor("#ba0000"));
                c.setBackgroundColor(Color.parseColor("#ba0000"));
                d.setBackgroundColor(Color.parseColor("#ba0000"));
                points = points+1;
                score.setText(points.toString());
                question2();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.setBackgroundColor(Color.parseColor("#006000"));
                b.setBackgroundColor(Color.parseColor("#ba0000"));
                c.setBackgroundColor(Color.parseColor("#ba0000"));
                d.setBackgroundColor(Color.parseColor("#ba0000"));
                saveScore();
                wrongAnswer();
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.setBackgroundColor(Color.parseColor("#006000"));
                b.setBackgroundColor(Color.parseColor("#ba0000"));
                c.setBackgroundColor(Color.parseColor("#ba0000"));
                d.setBackgroundColor(Color.parseColor("#ba0000"));
                saveScore();
                wrongAnswer();
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.setBackgroundColor(Color.parseColor("#006000"));
                b.setBackgroundColor(Color.parseColor("#ba0000"));
                c.setBackgroundColor(Color.parseColor("#ba0000"));
                d.setBackgroundColor(Color.parseColor("#ba0000"));
                saveScore();
                wrongAnswer();
            }
        });


    }


    public void question2(){

        question.setText("QUESTION 2");
        a.setBackgroundColor(Color.parseColor("#ffffff"));
        b.setBackgroundColor(Color.parseColor("#ffffff"));
        c.setBackgroundColor(Color.parseColor("#ffffff"));
        d.setBackgroundColor(Color.parseColor("#ffffff"));
        a.setText("A");
        b.setText("B");
        c.setText("C");
        d.setText("D");

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.setBackgroundColor(Color.parseColor("#ba0000"));
                b.setBackgroundColor(Color.parseColor("#006000"));
                c.setBackgroundColor(Color.parseColor("#ba0000"));
                d.setBackgroundColor(Color.parseColor("#ba0000"));
                saveScore();
                wrongAnswer();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.setBackgroundColor(Color.parseColor("#006000"));
                a.setBackgroundColor(Color.parseColor("#ba0000"));
                c.setBackgroundColor(Color.parseColor("#ba0000"));
                d.setBackgroundColor(Color.parseColor("#ba0000"));
                points = points+1;
                score.setText(points.toString());
                question1();
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.setBackgroundColor(Color.parseColor("#006000"));
                a.setBackgroundColor(Color.parseColor("#ba0000"));
                c.setBackgroundColor(Color.parseColor("#ba0000"));
                d.setBackgroundColor(Color.parseColor("#ba0000"));
                saveScore();
                wrongAnswer();
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.setBackgroundColor(Color.parseColor("#006000"));
                a.setBackgroundColor(Color.parseColor("#ba0000"));
                c.setBackgroundColor(Color.parseColor("#ba0000"));
                d.setBackgroundColor(Color.parseColor("#ba0000"));
                saveScore();
                wrongAnswer();
            }
        });


    }

    public void wrongAnswer(){
        saveScore();
        try {

            popUpScore(points+"", scoreFromDatabase);
        }
        catch (Exception ex) {
            Toast.makeText(this, ""+ex, Toast.LENGTH_SHORT).show();
        }

    }

    private void popUpScore(String curScore, String overScore) {

        Intent i = new Intent(getApplicationContext(), ScoreActivity.class);
        i.putExtra("curScore", curScore);
        i.putExtra("overScore", overScore);
        startActivity(i);
    }

}

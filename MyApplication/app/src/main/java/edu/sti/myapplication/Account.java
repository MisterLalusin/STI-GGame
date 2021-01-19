package edu.sti.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Account extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static  final String mypreference ="mypref";
    private Button logOutBTTN;
    Button play, leaderboard, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        play = findViewById(R.id.btnplay);
        leaderboard = findViewById(R.id.btnleaderboard);
        about = findViewById(R.id.btnabout);


        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), leaderboard.class);
                startActivity(intent);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), Science.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), About.class);
                startActivity(i);
            }
        });

        logOutBTTN = (Button)findViewById(R.id.btnsignout);
        logOutBTTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }

    public void logOut() {
        removeSession();
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);finish();
    }


    private void removeSession() {

        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("unameKey", null);
        editor.putString("passKey", null);
        editor.commit();
    }
}

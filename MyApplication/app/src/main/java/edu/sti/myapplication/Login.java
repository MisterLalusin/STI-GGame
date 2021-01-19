package edu.sti.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    Button btnLogin;
    EditText txtUname, txtPass;
    ImageView txtRegister;
    SharedPreferences sharedPreferences;
    public static  final String mypreference ="mypref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        try {
            activityLayout();
            sqliteDatabase();
            newAccount();
            currentSession();
        }
        catch (Exception ex) {
            Toast.makeText(this, ""+ex, Toast.LENGTH_SHORT).show();
        }

    }

    private void activityLayout() {
        btnLogin = findViewById(R.id.btnlogin);
        txtUname = findViewById(R.id.uname);
        txtPass = findViewById(R.id.pword);
        txtRegister = findViewById(R.id.redsignup);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
                finish();
            }
        });
    }



    private void sqliteDatabase(){
        openHelper = new DbHelper(this);
        db = openHelper.getReadableDatabase();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = txtUname.getText().toString();
                String pass = txtPass.getText().toString();


                cursor = db.rawQuery("SELECT * FROM tblStudents WHERE uname=? AND password=?", new String[]{uname, pass});

                if (uname.length() == 0 || pass.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please Fill the required fields!",Toast.LENGTH_LONG).show();
                }

                else if (cursor != null){
                    if (cursor.getCount() > 0){
                        Intent intent = new Intent (getApplicationContext(), Account.class);
                        intent.putExtra("intentUname", txtUname.getText().toString());
                        intent.putExtra("intentPassword", txtPass.getText().toString());
                        saveUser();
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Incorrect Details",Toast.LENGTH_SHORT).show();
                        removeSession();
                    }
                }
            }
        });
    }

    private void saveUser() {
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("unameKey", txtUname.getText().toString());
        editor.putString("passKey", txtPass.getText().toString());
        editor.commit();
    }

    public void newAccount(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String receivedUname = extras.getString("sendUname");
            String receivePass = extras.getString("sendPass");
            txtPass.setText(receivePass);
            txtUname.setText(receivedUname);
        }
    }

    private void currentSession(){
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.contains("unameKey")){
            txtUname.setText(sharedPreferences.getString("unameKey", ""));
            txtPass.setText(sharedPreferences.getString("passKey", ""));
            btnLogin.performClick();
            //Toast.makeText(getApplicationContext(),"CURRENT",Toast.LENGTH_SHORT).show();
        }
        else {
           // Toast.makeText(getApplicationContext(),"REMOVE",Toast.LENGTH_SHORT).show();
            removeSession();

        }
    }

    private void removeSession() {

        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("unameKey", null);
        editor.putString("passKey", null);
        editor.commit();
    }






}

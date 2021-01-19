package edu.sti.myapplication;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    Button btnSignup,btnLogin;
    EditText txtfname,txtmname,txtlname,txtuname,txtpassword,txtconfirmpassword,txtemail;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        activityLayout();
        sqliteDatabase();
    }

    private void sqliteDatabase() {
        openHelper = new DbHelper(this);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = openHelper.getReadableDatabase();
                String uname = txtuname.getText().toString();
                cursor = db.rawQuery("SELECT * FROM tblStudents WHERE uname = ?", new String[]{uname});


                if( !txtfname.getText().toString().equals("") || !txtmname.getText().toString().equals("")|| !txtlname.getText().toString().equals("")|| !txtuname.getText().toString().equals("")|| !txtpassword.getText().toString().equals("")|| !txtconfirmpassword.getText().toString().equals("")|| !txtemail.getText().toString().equals("")) {
                    if (txtpassword.getText().toString().equals(txtconfirmpassword.getText().toString())){

                        if (txtpassword.getText().toString().length() < 6){
                            Toast.makeText(getApplicationContext(), "Your password must be atleast 6 chracter",Toast.LENGTH_LONG).show();
                        }

                        else if (txtemail.getText().toString().length() == (txtemail.getText().toString().replace("@","").length()) ) {
                            Toast.makeText(getApplicationContext(), "Please check your Email Address",Toast.LENGTH_LONG).show();

                        }



                        else {
                            if (cursor != null) {
                                if (cursor.getCount() > 0) {
                                    Toast.makeText(Signup.this, "Username Is Already Used", Toast.LENGTH_SHORT).show();
                                }
                                /*Validation emman mamamaya*/
                                /*Validation emman mamamaya*/
                                /*Validation emman mamamaya*/
                                /*Validation emman mamamaya*/
                                /*Validation emman mamamaya*/
                                /*Validation emman mamamaya*/
                                else {
                                    db = openHelper.getWritableDatabase();
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("email", txtemail.getText().toString());
                                    contentValues.put("fname", txtfname.getText().toString());
                                    contentValues.put("mname", txtmname.getText().toString());
                                    contentValues.put("lname", txtlname.getText().toString());
                                    contentValues.put("uname", txtuname.getText().toString());
                                    contentValues.put("password", txtpassword.getText().toString());
                                    contentValues.put("friends", "0");
                                    contentValues.put("points", "0");
                                    contentValues.put("section", "");
                                    long id = db.insert("tblStudents", null, contentValues);
                                    Toast.makeText(Signup.this, "SIGN Up Successfull", Toast.LENGTH_SHORT).show();
                                    logIn();
                                }
                            }
                        }
                    }

                    else {
                        Toast.makeText(getApplicationContext(),"Please confirm your password",Toast.LENGTH_LONG).show();
                    }
                    ////////BREAK//////
                }

            }
        });
    }

    private void logIn() {
        Intent i = new Intent(getApplicationContext(), Login.class);
        i.putExtra("sendUname", txtuname.getText().toString());
        i.putExtra("sendPass", txtpassword.getText().toString());
        startActivity(i);
        finish();
    }

    private void activityLayout() {
        btnSignup = findViewById(R.id.btnsignup);
        txtfname = findViewById(R.id.nufname);
        txtmname = findViewById(R.id.numname);
        txtlname = findViewById(R.id.nulname);
        txtuname = findViewById(R.id.nuuname);
        txtpassword = findViewById(R.id.nupword);
        txtconfirmpassword = findViewById(R.id.nucpword);
        txtemail = findViewById(R.id.nuemail);
        btnLogin = findViewById(R.id.redlogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }


}

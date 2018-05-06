package com.example.quanpn.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;

public class register extends AppCompatActivity {

    EditText name,email,pass;
    Button register,login;
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name= (EditText) findViewById(R.id.name);
        email= (EditText) findViewById(R.id.email);
        pass= (EditText) findViewById(R.id.pass);
        register= (Button) findViewById(R.id.register);

        connectionClass = new ConnectionClass();

        progressDialog=new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doregister a=new Doregister();
                a.execute("");

            }
        });



    }

    public class Doregister extends AsyncTask<String,String,String>
    {

        String namestr=name.getText().toString();
        String emailstr=email.getText().toString();
        String passstr=pass.getText().toString();
        String z="Tên tài khoản đã tồn tại";
        boolean isSuccess=false;
        String nm,password;
        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            if(namestr.trim().equals("")|| emailstr.trim().equals("") ||passstr.trim().equals(""))
                z = "Please enter all fields....";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        isSuccess=true;
                        String query1="insert into user values('"+namestr+"','"+namestr+"','"+passstr+"','"+emailstr+"')";
                        Statement stmt1 = con.createStatement();
                        stmt1.executeUpdate(query1);
                        z = "Register successfull";


                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();
            if(isSuccess) {
                startActivity(new Intent(register.this,menu.class));

            }
            progressDialog.hide();
        }
    }



}




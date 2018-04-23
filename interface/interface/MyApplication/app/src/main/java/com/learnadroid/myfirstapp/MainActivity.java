package com.learnadroid.myfirstapp;

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
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity  {

    EditText name,email,pass;
    Button register,login;
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        name= (EditText) findViewById(R.id.name);
        pass= (EditText) findViewById(R.id.pass);
        register= (Button) findViewById(R.id.register);
        login= (Button) findViewById(R.id.login);

        connectionClass = new ConnectionClass();

        progressDialog=new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              startActivity(new Intent(MainActivity.this,register.class));
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dologin dologin=new Dologin();
                dologin.execute();
            }
        });


    }


    private class Dologin extends AsyncTask<String,String,String>{


        String namestr=name.getText().toString();
        String passstr=pass.getText().toString();
        String z="";
        boolean isSuccess=false;

        String nm,em,password;


        @Override
        protected void onPreExecute() {


            progressDialog.setMessage("Loading...");
            progressDialog.show();


            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            if(namestr.trim().equals("") ||passstr.trim().equals(""))
                z = "Please enter all fields....";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query=" select  * from demoregister where namestr='"+namestr+"' and passstr = '"+passstr+"'";


                        Statement stmt = con.createStatement();
                       // stmt.executeUpdate(query);

                        ResultSet rs=stmt.executeQuery(query);

                        while (rs.next())

                        {
                            nm= rs.getString(1);
                            password=rs.getString(3);


                   if(nm.equals(namestr)&&password.equals(passstr))
                            {

                                isSuccess=true;
                                z = "Login successfull";

                            }

                            else
                   {isSuccess=true;
                                z="a";
                                }
                        }

                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                }
            }
            return z;        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


            if(isSuccess) {

                Intent intent=new Intent(MainActivity.this,quanlythu.class);

                intent.putExtra("name",namestr);

                startActivity(intent);
            }


            progressDialog.hide();

        }
    }
}

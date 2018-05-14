package com.learnadroid.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  {

    EditText Username,Password;
    Button SingIn,SingUp;

    private String url = "https://quanpn.000webhostapp.com/manage/user.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SingIn=(Button) findViewById(R.id.btn_signIn);
        SingUp=(Button) findViewById(R.id.btn_signUp);
       Username=(EditText) findViewById(R.id.txtUsername);
       Password=(EditText) findViewById(R.id.txtPassword);
       SingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap(url,Username.getText().toString(),Password.getText().toString());
            }
        });
        SingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle mm = new Bundle();
                mm.putString("mmm","c");
                Intent c = new Intent(MainActivity.this, register.class);
                c.putExtra("mmxfhhh", mm);
                startActivity(c);
            }
        });

    }

    protected void DangNhap(String url, final String a,final String b){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    if(a.equals(obj.getString("userName"))&&b.equals(obj.getString("password")))
                                    {
                                        Bundle mm = new Bundle();
                                        mm.putString("Laytendangnhap",a);
                                        Intent c = new Intent(MainActivity.this, menu.class);
                                        c.putExtra("data", mm);
                                        startActivity(c);
                                        break;
                                    }
                                    else {
                                        Toast.makeText(getBaseContext(),""+"Thông tin tài khoản hoặc mật khẩu không chính xác",Toast.LENGTH_LONG).show();
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }


}

package com.learnadroid.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    EditText Username,Email,Password;
    Button SignUp;
    private String urlInsert="https://quanpn.000webhostapp.com/manage/InsertUser.php";
    private String url = "https://quanpn.000webhostapp.com/manage/user.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Username=(EditText) findViewById(R.id.txtUsername) ;
        Email=(EditText) findViewById(R.id.txtEmail) ;
        Password=(EditText) findViewById(R.id.txtPassword) ;
        SignUp=(Button) findViewById(R.id.btn_signUp) ;
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp(url,Username.getText().toString().trim());

            }
        });


    }

    private void addTypeExpense(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(register.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(register.this,"Thêm thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(register.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userID",Username.getText().toString().trim());
                params.put("userName",Username.getText().toString().trim());
                params.put("password", Password.getText().toString().trim());
                params.put("Email", Email.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    protected void SignUp(String url, final String a){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    if(a.equals(obj.getString("userName")))
                                    {
                                        Toast.makeText(getBaseContext(),""+"Tài khoản đã tồn tại",Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        addTypeExpense();
                                        Bundle mm = new Bundle();
                                        mm.putString("Laytendangnhap","a");
                                        Intent c = new Intent(register.this, menu.class);
                                        c.putExtra("data", mm);
                                        startActivity(c);
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
                        Toast.makeText(register.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }



}




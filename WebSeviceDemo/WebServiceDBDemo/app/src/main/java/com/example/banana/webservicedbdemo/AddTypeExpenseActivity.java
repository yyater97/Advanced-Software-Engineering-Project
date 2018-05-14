package com.example.banana.webservicedbdemo;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AddTypeExpenseActivity extends AppCompatActivity {

    private String url = "https://demowebservice.000webhostapp.com/WebServiceDBDemo/insert.php";
    private Button btnInsert;
    private EditText edtTypeExpenseName, edtDiscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type_expense);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        edtTypeExpenseName = (EditText) findViewById(R.id.edtTypeExpenseName);
        edtDiscription = (EditText) findViewById(R.id.edtDescription);

        btnInsert.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String TypeExpenseName = edtTypeExpenseName.getText().toString().trim();
                String Description = edtDiscription.getText().toString().trim();
                if(TypeExpenseName.isEmpty()||Description.isEmpty()){
                    Toast.makeText(AddTypeExpenseActivity.this,"Vui lòng điền đầy đủ thông tin để thêm!",Toast.LENGTH_LONG).show();
                }else{
                    addTypeExpense();
                }

            }
        });
    }

    private void addTypeExpense(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(AddTypeExpenseActivity.this,"Thêm thành công",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddTypeExpenseActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(AddTypeExpenseActivity.this,"Thêm thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddTypeExpenseActivity.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("type_expenseID","TE20");
                params.put("type_expenseName",edtTypeExpenseName.getText().toString().trim());
                params.put("description", edtDiscription.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}

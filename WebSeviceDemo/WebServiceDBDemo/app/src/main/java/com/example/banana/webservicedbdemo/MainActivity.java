package com.example.banana.webservicedbdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String url = "https://demowebservice.000webhostapp.com/WebServiceDBDemo/index.php";
    private ListView lvTypeExpenseList;
    private ArrayList<TypeExpense> arrayTypeExpense;
    TypeExpenseApdater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh xạ các biến
        lvTypeExpenseList = (ListView) findViewById(R.id.lvTypeExpenseList);
        arrayTypeExpense = new ArrayList<>();

        adapter = new TypeExpenseApdater(this, R.layout.activity_type_expense_item,arrayTypeExpense);
        lvTypeExpenseList.setAdapter(adapter);

        ReadJSON(url);
    }

    protected void ReadJSON(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){

                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                arrayTypeExpense.add(new TypeExpense(
                                        obj.getString("type_expenseName"),
                                        obj.getString("description")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
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

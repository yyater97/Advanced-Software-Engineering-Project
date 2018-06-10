package com.learnadroid.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tracutygia extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        String url="https://quanpn.000webhostapp.com/manage/selectTygia.php";
        EditText fillText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracutygia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final List<lisviewBaocao> image_details = new ArrayList<lisviewBaocao>();

        final ListView listView = (ListView) findViewById(R.id.listView1);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fillText=(EditText) findViewById(R.id.txt_Giatri);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    image_details.add(new lisviewBaocao(obj.getString("Ten"),obj.getString("Ghichu"),obj.getString("Tygia")+" Đ",""));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            listView.setAdapter(new customlistviewBaocao(tracutygia.this,image_details));
                        }

                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(tracutygia.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
        fillText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listView.setAdapter(null);
                if(fillText.getText().toString().equals("")) {
                    final RequestQueue requestQueue = Volley.newRequestQueue(tracutygia.this);
                    final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONArray>(){

                                @Override

                                public void onResponse(JSONArray response) {
                                    for(int i = 0; i<response.length(); i++){
                                        try {
                                            JSONObject obj = response.getJSONObject(i);
                                            {
                                                image_details.add(new lisviewBaocao(obj.getString("Ten"),obj.getString("Ghichu"),obj.getString("Tygia")+" Đ",""));
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        listView.setAdapter(new customlistviewBaocao(tracutygia.this,image_details));
                                    }

                                }
                            },
                            new Response.ErrorListener(){

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(tracutygia.this, error.toString(), Toast.LENGTH_LONG).show();
                                }
                            }
                    );
                    requestQueue.add(jsonArrayRequest);
                }
                else {
                    listView.setAdapter(null);
                }

            }
            @Override
            public void afterTextChanged(Editable s) {
                final List<lisviewBaocao> list = new ArrayList<lisviewBaocao>();
                RequestQueue requestQueue = Volley.newRequestQueue(tracutygia.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/SelectTygia.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray arr = new JSONArray(response);
                            for (int i = 0; i < arr.length(); i++) {
                                try {
                                    JSONObject obj = arr.getJSONObject(i);
                                    {

                                       list.add(new lisviewBaocao(obj.getString("Ten"),obj.getString("Ghichu"),obj.getString("Tygia")+" Đ",""));

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                listView.setAdapter(new customlistviewBaocao(tracutygia.this,list));

                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(tracutygia.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("Ten",fillText.getText().toString());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);

            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tracutygia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.danhsachchi) {
            Intent intent=new Intent(tracutygia.this,Danhmucchi.class);
            startActivity(intent);
        }
        else if(id==R.id.danhsachthu)
        {
            Intent intent=new Intent(tracutygia.this,activity_Danhsachthu.class);
            startActivity(intent);
        }
        else if(id==R.id.trangchu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(tracutygia.this, menu.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        else if(id==R.id.Baocao)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(tracutygia.this, baocao.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        else if(id==R.id.lich)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(tracutygia.this, datetime.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        else if(id==R.id.quanlythu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(tracutygia.this, quanlythu.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        else if(id==R.id.quanlychi)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(tracutygia.this, quanlychi.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        else if(id==R.id.cactaikhoan)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(tracutygia.this, taikhoan.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        else if(id==R.id.tracuu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(tracutygia.this, tracutygia.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

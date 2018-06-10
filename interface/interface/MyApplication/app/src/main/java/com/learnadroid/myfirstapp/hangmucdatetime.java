package com.learnadroid.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class hangmucdatetime extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String urlSelectExpense = "https://quanpn.000webhostapp.com/manage/selectExpenseUserID.php";
    String urlSelectIncome = "https://quanpn.000webhostapp.com/manage/selectIncomeUserID.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangmucdatetime);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent a=getIntent();
        Bundle bundle = a.getBundleExtra("getUser");
        final String Keys=bundle.getString("Keys");
        bundle=a.getBundleExtra("getUser1");
        Toast.makeText(hangmucdatetime.this,Keys,Toast.LENGTH_LONG).show();
        final String Keys1=bundle.getString("Keys1");
        final List<Income> image_details = new ArrayList<Income>();
        final ListView listView = (ListView) findViewById(R.id.listView1);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlSelectExpense, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    for (int i = 0; i < arr.length(); i++) {
                        try {
                            JSONObject obj = arr.getJSONObject(i);
                            {

                                if (Keys.equals(obj.getString("Ngaychi"))) {

                                    image_details.add(new Income("Chi: " + obj.getString("Mucchi"), "anuong", Integer.parseInt(obj.getString("Giatri")), "Tài khoản: " + obj.getString("accountID"), obj.getInt("ExpenseID")));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        listView.setAdapter(new CustomIncomeApdater(hangmucdatetime.this,image_details));
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(hangmucdatetime.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userID",Keys1);
                return params;
            }
        };
        requestQueue.add(stringRequest);
        stringRequest = new StringRequest(Request.Method.POST, urlSelectIncome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    for (int i = 0; i < arr.length(); i++) {
                        try {
                            JSONObject obj = arr.getJSONObject(i);
                            {
                                {
                                    if (Keys.equals(Remove(obj.getString("Ngaychi")))) {
                                        image_details.add(new Income("Thu: " + obj.getString("Mucchi"), "money", Integer.parseInt(obj.getString("Giatri")), "Tài khoản: " + obj.getString("accountID"), obj.getInt("IncomeID")));
                                    }
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        listView.setAdapter(new CustomIncomeApdater(hangmucdatetime.this,image_details));
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(hangmucdatetime.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userID",Keys1);
                return params;
            }
        };
        requestQueue.add(stringRequest);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        getMenuInflater().inflate(R.menu.hangmucdatetime, menu);
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
    public String Remove(String String)
    {
        String=String.replace("-0","-");
        return String;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.danhsachchi) {
            Intent intent=new Intent(hangmucdatetime.this,Danhmucchi.class);
            startActivity(intent);
        }
        else if(id==R.id.danhsachthu)
        {
            Intent intent=new Intent(hangmucdatetime.this,activity_Danhsachthu.class);
            startActivity(intent);
        }
        else if(id==R.id.trangchu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(hangmucdatetime.this, menu.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        else if(id==R.id.Baocao)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(hangmucdatetime.this, baocao.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        else if(id==R.id.lich)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(hangmucdatetime.this, datetime.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        else if(id==R.id.quanlythu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(hangmucdatetime.this, quanlythu.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        else if(id==R.id.quanlychi)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(hangmucdatetime.this, quanlychi.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        else if(id==R.id.cactaikhoan)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(hangmucdatetime.this, taikhoan.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        else if(id==R.id.tracuu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(hangmucdatetime.this, tracutygia.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

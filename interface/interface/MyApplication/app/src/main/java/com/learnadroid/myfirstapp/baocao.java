package com.learnadroid.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class baocao extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String urlInsert = "https://quanpn.000webhostapp.com/manage/select.php";
    String urlSelectExpense = "https://quanpn.000webhostapp.com/manage/selectExpenseUserID.php";
    String urlSelectIncome = "https://quanpn.000webhostapp.com/manage/selectIncomeUserID.php";
    Calendar calendar = Calendar.getInstance();
    int Ngay = 0;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baocao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      ListView listView = (ListView) findViewById(R.id.listView1);
        List<lisviewBaocao> list=new ArrayList<lisviewBaocao>();
        list.add(new lisviewBaocao("HÔM NAY","today",String.valueOf(calendar.get(Calendar.DATE))+"-"+String.valueOf(calendar.get(Calendar.MONTH)+1)+"-"+String.valueOf(calendar.get(Calendar.YEAR)),String.valueOf(calendar.get(Calendar.YEAR))+"-"+String.valueOf(calendar.get(Calendar.MONTH)+1)+"-"+String.valueOf(calendar.get(Calendar.DATE))));
        list.add(new lisviewBaocao("THÁNG NÀY","thangnay",String.valueOf(calendar.get(Calendar.MONTH)+1)+"-"+String.valueOf(calendar.get(Calendar.YEAR)),String.valueOf(calendar.get(Calendar.YEAR))+"-"+String.valueOf(calendar.get(Calendar.MONTH)+1)));
        list.add(new lisviewBaocao("NĂM NAY","namnay",String.valueOf(calendar.get(Calendar.YEAR)),String.valueOf(calendar.get(Calendar.YEAR))));
        listView.setAdapter(new customlistviewBaocao(baocao.this, list));
        final Intent a = getIntent();
        Bundle bundle = a.getBundleExtra("getUser");
        final String Keys=bundle.getString("Keys");
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
                                Ngay+=Integer.parseInt(obj.getString("Giatri"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(baocao.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userID","a");
                return params;
            }
        };
        requestQueue.add(stringRequest);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                text=(TextView) v.findViewById(R.id.textView7);
                Bundle bundle = new Bundle();
                bundle.putString("Keys",text.getText().toString());
                Intent intent = new Intent(baocao.this, mainbaocao.class);
                intent.putExtra("getUser", bundle);
                bundle.putString("Keys1",Keys);
                intent.putExtra("getUser1",bundle);
                startActivity(intent);
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
        getMenuInflater().inflate(R.menu.baocao, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.danhsachchi) {
            Intent intent=new Intent(baocao.this,Danhmucchi.class);
            startActivity(intent);
        }
        else if(id==R.id.danhsachthu)
        {
            Intent intent=new Intent(baocao.this,activity_Danhsachthu.class);
            startActivity(intent);
        }
        else if(id==R.id.trangchu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(baocao.this, menu.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        else if(id==R.id.Baocao)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(baocao.this, baocao.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        else if(id==R.id.lich)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(baocao.this, datetime.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        else if(id==R.id.quanlythu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(baocao.this, quanlythu.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        else if(id==R.id.quanlychi)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(baocao.this, quanlychi.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        else if(id==R.id.cactaikhoan)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(baocao.this, taikhoan.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        else if(id==R.id.tracuu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(baocao.this, tracutygia.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

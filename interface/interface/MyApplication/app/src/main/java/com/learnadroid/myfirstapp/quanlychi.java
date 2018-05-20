package com.learnadroid.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

public class quanlychi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Spinner SpinnerTaikhoan,SpinnerMucchi;
    ConnectionClass connectionClass;
    TextView a;

    private List<String> list ;
    private List<String> list1;
    EditText Giatri,Ngaychi,Tuai,Ghichu;
   Button Luulai;
    private String url = "https://quanpn.000webhostapp.com/manage/selectKhoanchi.php";
    private String urlAccount = "https://quanpn.000webhostapp.com/manage/selectAccount.php";
    private String urlInsert = "https://quanpn.000webhostapp.com/manage/insertExpense.php";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlychi);
        final Intent a = getIntent();
        Bundle bundle = a.getBundleExtra("getUser");
        final String Keys=bundle.getString("Keys");
        SpinnerTaikhoan=(Spinner) findViewById(R.id.sp_Taikhoan);
        SpinnerMucchi=(Spinner) findViewById(R.id.sp_Mucchi);
        Giatri=(EditText) findViewById(R.id.txt_Giatri);
        Ngaychi=(EditText) findViewById(R.id.txt_Ngaychi);
        Ghichu=(EditText) findViewById(R.id.txt_Ghichu);
        Tuai=(EditText) findViewById(R.id.txt_Denai);
        Luulai=(Button) findViewById(R.id.btn_Luulai);
        Taikhoan(urlAccount);
        Mucthu(url);
        InsertExpense(Keys,SpinnerTaikhoan.getSelectedItem().toString(),SpinnerMucchi.getSelectedItem().toString());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(quanlychi.this,menu.class);
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
        getMenuInflater().inflate(R.menu.quanlychi, menu);
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
    public void Mucthu(String url1)
    {
        list=new ArrayList<>();
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url1, null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    list.add(obj.getString("detail_type_expenseName"));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter(quanlychi.this, android.R.layout.simple_spinner_item,list);
                            adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                            SpinnerMucchi.setAdapter(adapter);
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(quanlychi.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    public void Taikhoan(String url1)
    {
        list1=new ArrayList<>();
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url1, null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    list1.add(obj.getString("accountName"));

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter(quanlychi.this, android.R.layout.simple_spinner_item,list1);
                            adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                            SpinnerTaikhoan.setAdapter(adapter);
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(quanlychi.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void InsertExpense(final String Key,final String KeySpinnerTaikhoan,final String KeySpninerMuccchi){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(quanlychi.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(quanlychi.this,"Thêm thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(quanlychi.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("accountID",Key+"-"+KeySpinnerTaikhoan);
                params.put("Giatri",Giatri.getText().toString());
                params.put("Mucchi",KeySpninerMuccchi );
                params.put("Ngaychi", Ngaychi.getText().toString());
                params.put("Ghichu", Ghichu.getText().toString());
                params.put("Hangmuc", "Chi");
                params.put("Denai",Tuai.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

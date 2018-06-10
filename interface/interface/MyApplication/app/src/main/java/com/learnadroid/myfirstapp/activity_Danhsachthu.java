package com.learnadroid.myfirstapp;

import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class activity_Danhsachthu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Dialog dialog,dialogThaydoidanhmuc;
    EditText txtHangmuc,txtDiengiai,txtHanngmucthaydoi,txtDiengiaithaydoi;
    TextView MaHangmuc,Tenhangmuc,Diengiai;
    Button Themhangmuc,Thaydoithongtin,Xoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhmucchi);
        dialog=new Dialog(activity_Danhsachthu.this);
        dialogThaydoidanhmuc=new Dialog(activity_Danhsachthu.this);
        dialog.setContentView(R.layout.insert_thuchi);
        dialogThaydoidanhmuc.setContentView(R.layout.change_thuchi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtHangmuc=(EditText) dialog.findViewById(R.id.txtHangmuc);
        txtDiengiai=(EditText) dialog.findViewById(R.id.txtDiengiai);
        final List<Danhmucthumua> image_details = new ArrayList<Danhmucthumua>();
        final ListView listView = (ListView) findViewById(R.id.listView1);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Themhangmuc=(Button) dialog.findViewById(R.id.btn_Capnhathongtin);
        txtHanngmucthaydoi=(EditText) dialogThaydoidanhmuc.findViewById(R.id.txtHangmuc);
        txtDiengiaithaydoi=(EditText) dialogThaydoidanhmuc.findViewById(R.id.txtDiengiai);
        Thaydoithongtin=(Button) dialogThaydoidanhmuc.findViewById(R.id.btn_Capnhathongtin);
        Xoa=(Button) dialogThaydoidanhmuc.findViewById(R.id.btn_Xoa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Themhangmuc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Insert();
                    }
                });
                dialog.show();
            }
        });
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://quanpn.000webhostapp.com/manage/Laykhoanthu.php", null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    if("Lương".equals(obj.getString("type_incomeName"))) {
                                        image_details.add(new Danhmucthumua(obj.getString("type_incomeName"), "tienluong", obj.getString("description"),obj.getString("type_incomeID")));
                                    }
                                    else if(obj.getString("type_incomeName").equals("Thưởng")) {
                                        image_details.add(new Danhmucthumua(obj.getString("type_incomeName"), "thuong", obj.getString("description"),obj.getString("type_incomeID")));
                                    }
                                    else if(obj.getString("type_incomeName").equals("Được cho/tặng")) {
                                        image_details.add(new Danhmucthumua(obj.getString("type_incomeName"), "lixi", obj.getString("description"),obj.getString("type_incomeID")));
                                    }
                                    else if(obj.getString("type_incomeName").equals("Lãi tiết kiệm")) {
                                        image_details.add(new Danhmucthumua(obj.getString("type_incomeName"), "tienlai", obj.getString("description"),obj.getString("type_incomeID")));
                                    }
                                    else  {
                                        image_details.add(new Danhmucthumua(obj.getString("type_incomeName"), "thukhac", obj.getString("description"),obj.getString("type_incomeID")));
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            listView.setAdapter(new CustomDanhmucmuaApdater(activity_Danhsachthu.this, image_details));
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity_Danhsachthu.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                MaHangmuc=(TextView) v.findViewById(R.id.textViewCohieu);
                Tenhangmuc=(TextView) v.findViewById(R.id.textView_countryName);
                Diengiai=(TextView) v.findViewById(R.id.textView_population);
                txtHanngmucthaydoi.setText(Remove(Tenhangmuc.getText().toString()));
                txtDiengiaithaydoi.setText(Remove(Diengiai.getText().toString()));
                Thaydoithongtin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Update(MaHangmuc.getText().toString());
                    }
                });
                Xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Delete(MaHangmuc.getText().toString());
                    }
                });
                dialogThaydoidanhmuc.show();
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
        getMenuInflater().inflate(R.menu.activity__danhsachthu, menu);
        return true;
    }
    public String Remove(String String)
    {
        String=String.replace("Hạng mục: ","");
        String=String.replace("Diễn giải: ","");
        return String;
    }
    private void InsertMucchi(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/insertTypeIncome.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(activity_Danhsachthu.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(activity_Danhsachthu.this,"Thêm thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity_Danhsachthu.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("type_incomeName",txtHangmuc.getText().toString());
                params.put("description",txtDiengiai.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    private void UpdateMuchi(String a){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/updateTypeIncome.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(activity_Danhsachthu.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(activity_Danhsachthu.this,"Thêm thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity_Danhsachthu.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("type_incomeID",txtHangmuc.getText().toString());
                params.put("type_incomeName",txtHanngmucthaydoi.getText().toString());
                params.put("description",txtDiengiaithaydoi.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    public void Insert()
    {

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://quanpn.000webhostapp.com/manage/Laykhoanthu.php", null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    if (txtHangmuc.getText().toString().equals(obj.getString("type_incomeName")))
                                    {
                                        Toast.makeText(getBaseContext(),""+"Danh mục đã tồn tại",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        InsertMucchi();
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
                        Toast.makeText(activity_Danhsachthu.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    public void Update(final String a)
    {

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://quanpn.000webhostapp.com/manage/Laykhoanthu.php", null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    if (txtHanngmucthaydoi.getText().toString().equals(obj.getString("type_incomeName")))
                                    {
                                        Toast.makeText(getBaseContext(),""+"Danh mục đã tồn tại",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        UpdateMuchi(a);
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
                        Toast.makeText(activity_Danhsachthu.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void Delete(final String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/deleteTypeIncome.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(activity_Danhsachthu.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(activity_Danhsachthu.this,"Thêm thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity_Danhsachthu.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("type_incomeID",url);
                return params;
            }
        };
        requestQueue.add(stringRequest);

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

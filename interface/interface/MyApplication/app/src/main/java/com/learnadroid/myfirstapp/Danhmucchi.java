package com.learnadroid.myfirstapp;

import android.app.Dialog;
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
import android.widget.AdapterView;
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

public class Danhmucchi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Dialog dialog,dialogThaydoidanhmuc   ;
    EditText txtHangmuc,txtDiengiai,txtHanngmucthaydoi,txtDiengiaithaydoi;
    TextView MaHangmuc,Tenhangmuc,Diengiai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhmucchi);
        dialog=new Dialog(Danhmucchi.this);
        dialogThaydoidanhmuc=new Dialog(Danhmucchi.this);
        dialog.setContentView(R.layout.insert_thuchi);
        dialogThaydoidanhmuc.setContentView(R.layout.change_thuchi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtHangmuc=(EditText) dialog.findViewById(R.id.txtHangmuc);
        txtDiengiai=(EditText) dialog.findViewById(R.id.txtDiengiai);
        txtHanngmucthaydoi=(EditText) dialogThaydoidanhmuc.findViewById(R.id.txtHangmuc);
        txtDiengiaithaydoi=(EditText) dialogThaydoidanhmuc.findViewById(R.id.txtDiengiai);
        final List<Danhmucthumua> image_details = new ArrayList<Danhmucthumua>();
        final ListView listView = (ListView) findViewById(R.id.listView1);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://quanpn.000webhostapp.com/manage/selectKhoanchi.php", null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    if("Đi chợ/siêu thị".equals(obj.getString("detail_type_expenseName"))) {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "dicho", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Xăng xe".equals(obj.getString("detail_type_expenseName"))) {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "xangxe", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }

                                    else if ("Bảo hiểm xe".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "hocphi", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Sửa chữa, bảo dưỡng xe".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "suaxe", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Gửi xe".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "baohiem", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Thuê xe/Grab/Taxi/Xe ôm".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "hocphi", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Học phí".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "money", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Sách vở - Đồ dùng học tập".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "sachvo", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Sữa".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "sua", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Đồ chơi".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "dochoi", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Tiền tiêu vặt".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "money", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Ăn tiệm/cơm quán".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "foot", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Quần áo".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "ao", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Giầy dép".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "giay", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Đồ điện tử".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "uong", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Phụ kiện khác".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "quan", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Cưới xin".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "damcuoi", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Khám chữa bệnh - Bảo hiểm y tế".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "baohiem", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Thăm hỏi".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "qua", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Biếu tặng".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "qua", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Cafe/Trà sữa".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "cafe", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Sửa chữa nhà cửa".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "suachua", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else if ("Mua sắm đồ đạc".equals(obj.getString("detail_type_expenseName")))
                                    {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "ao1", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }
                                    else {
                                        image_details.add(new Danhmucthumua(obj.getString("detail_type_expenseName"), "quan", obj.getString("description"),obj.getString("detail_type_expenseID")));
                                    }

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            listView.setAdapter(new CustomDanhmucmuaApdater(Danhmucchi.this, image_details));
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Danhmucchi.this, error.toString(), Toast.LENGTH_LONG).show();
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
    private void InsertMucchi(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/insertTypeExpense.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(Danhmucchi.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(Danhmucchi.this,"Thêm thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Danhmucchi.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("detail_type_expenseName",txtHangmuc.getText().toString());
                params.put("description",txtDiengiai.getText().toString());
                params.put("type_expenseID","TE001");
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    public String Remove(String String)
    {
        String=String.replace("Hạng mục: ","");
        String=String.replace("Diễn giải: ","");
        return String;
    }
    private void UpdateMuchi(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/updateTypeExpense.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(Danhmucchi.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(Danhmucchi.this,"Thêm thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Danhmucchi.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("detail_type_expenseName",txtHangmuc.getText().toString());
                params.put("description",txtDiengiai.getText().toString());
                params.put("type_expenseID","TE001");
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    public void Insert(String url1)
    {

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url1, null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    if (Tenhangmuc.getText().toString().equals(obj.getString("type_incomeName")))
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
                        Toast.makeText(Danhmucchi.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    public void Update(String url1)
    {

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url1, null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    if (Tenhangmuc.getText().toString().equals(obj.getString("detail_type_expenseName")))
                                    {
                                        Toast.makeText(getBaseContext(),""+"Danh mục đã tồn tại",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        UpdateMuchi();
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
                        Toast.makeText(Danhmucchi.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void Delete(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(Danhmucchi.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(Danhmucchi.this,"Thêm thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Danhmucchi.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("type_incomeID",txtHangmuc.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.danhmucchi, menu);
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

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://quanpn.000webhostapp.com/manage/selectKhoanchi.php", null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    if (Tenhangmuc.getText().toString().equals(obj.getString("type_incomeName")))
                                    {
                                        Toast.makeText(getBaseContext(),""+"Tài khoản đã tồn tại",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {

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
                        Toast.makeText(Danhmucchi.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.danhsachchi) {
            Intent intent=new Intent(Danhmucchi.this,Danhmucchi.class);
            startActivity(intent);
        }
        else if(id==R.id.danhsachthu)
        {
            Intent intent=new Intent(Danhmucchi.this,activity_Danhsachthu.class);
            startActivity(intent);
        }
        else if(id==R.id.trangchu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(Danhmucchi.this, menu.class);
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
            Intent intent = new Intent(Danhmucchi.this, baocao.class);
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
            Intent intent = new Intent(Danhmucchi.this, datetime.class);
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
            Intent intent = new Intent(Danhmucchi.this, quanlythu.class);
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
            Intent intent = new Intent(Danhmucchi.this, quanlychi.class);
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
            Intent intent = new Intent(Danhmucchi.this, taikhoan.class);
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
            Intent intent = new Intent(Danhmucchi.this, tracutygia.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

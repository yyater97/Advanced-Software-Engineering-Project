package com.learnadroid.myfirstapp;

import android.app.Dialog;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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

import static com.android.volley.Response.ErrorListener;

public class baocaothuAccount extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Dialog dialog;
    EditText Giatri,Ngaychi,Ghichu,Tuai;
    Spinner SpinnerTaikhoan,SpinnerMucchi;
    TextView textViewMaGD,textViewCohieu;
    String urlInsert = "https://quanpn.000webhostapp.com/manage/select.php";
    String urlSelectIncome = "https://quanpn.000webhostapp.com/manage/selectIncome.php";
    String urlSelectExpense = "https://quanpn.000webhostapp.com/manage/select.php";
    private String url = "https://quanpn.000webhostapp.com/manage/Laykhoanthu.php";
    private String url1 = "https://quanpn.000webhostapp.com/manage/selectKhoanchi.php";
    private String urlAccount = "https://quanpn.000webhostapp.com/manage/selectAccount.php";
    private List<String> list ;
    private List<String> list1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        final Intent a = getIntent();
        super.onCreate(savedInstanceState);
        Bundle bundle = a.getBundleExtra("getUser");
        final String Keys=bundle.getString("Keys");
        bundle=a.getBundleExtra("getUser1");
        final String Keys1=bundle.getString("Keys1");
        bundle=a.getBundleExtra("getUser2");
        final String Keys2=bundle.getString("Keys2");
        setContentView(R.layout.activity_baocaothu_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dialog=new Dialog(baocaothuAccount.this);
        dialog.setContentView(R.layout.change_list_thu_chi);
        SpinnerTaikhoan=(Spinner) dialog.findViewById(R.id.sp_Taikhoan);
        SpinnerMucchi=(Spinner) dialog.findViewById(R.id.sp_Mucchi);
        Giatri=(EditText) dialog.findViewById(R.id.txt_Giatri);
        Ngaychi=(EditText) dialog.findViewById(R.id.txt_Ngaychi);
        Ghichu=(EditText) dialog.findViewById(R.id.txt_Ghichu);
        Tuai=(EditText) dialog.findViewById(R.id.txt_Denai);

        final List<Income> image_details = new ArrayList<Income>();
        final ListView listView = (ListView) findViewById(R.id.listView1);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    for (int i = 0; i < arr.length(); i++) {
                        try {
                            JSONObject obj = arr.getJSONObject(i);
                            {
                                image_details.add(new Income("Thu: "+obj.getString("Mucchi"), "thutien", Integer.parseInt(obj.getString("Giatri")), obj.getString("Ngaychi"),obj.getInt("ExpenseID")));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listView.setAdapter(new CustomIncomeApdater(baocaothuAccount.this, image_details));
                    }
                } catch (JSONException ex) {
                        ex.printStackTrace();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(baocaothuAccount.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("accountID",Keys);
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
                                image_details.add(new Income("Chi: "+obj.getString("Mucchi"), "tienlai", Integer.parseInt(obj.getString("Giatri")), obj.getString("Ngaychi"),obj.getInt("IncomeID")));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listView.setAdapter(new CustomIncomeApdater(baocaothuAccount.this, image_details));
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(baocaothuAccount.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("accountID",Keys);
                return params;
            }
        };

        requestQueue.add(stringRequest);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                textViewMaGD=(TextView) v.findViewById(R.id.textViewMagiaodich);
                textViewCohieu=(TextView) v.findViewById(R.id.textView_countryName);
                Taikhoan(urlAccount,Keys1);
               if(textViewCohieu.getText().toString().startsWith("C")==true)
               {
                   MucChi(url1);

               }
                if(textViewCohieu.getText().toString().startsWith("T")==true)
                {
                    Mucthu(url);
                }

                dialog.show();
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
                                    list.add(obj.getString("type_incomeName"));

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter(baocaothuAccount.this, android.R.layout.simple_spinner_item,list);
                            adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                            SpinnerMucchi.setAdapter(adapter);
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(baocaothuAccount.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    public void Taikhoan(String url1,final String Keys)
    {
        list1=new ArrayList<>();
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    for(int i = 0; i<response.length(); i++){
                        try {
                            JSONObject obj = arr.getJSONObject(i);
                            {
                                list1.add(obj.getString("accountName"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter(baocaothuAccount.this, android.R.layout.simple_spinner_item,list1);
                        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                        SpinnerTaikhoan.setAdapter(adapter);
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(baocaothuAccount.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userID",Keys);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void MucChi(String url1)
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
                            ArrayAdapter<String> adapter = new ArrayAdapter(baocaothuAccount.this, android.R.layout.simple_spinner_item,list);
                            adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                            SpinnerMucchi.setAdapter(adapter);
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(baocaothuAccount.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    public void Income(String url,final String Keys)
    {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    for (int i = 0; i < arr.length(); i++) {
                        try {
                            JSONObject obj = arr.getJSONObject(i);
                            {
                                Giatri.setText(obj.getString("Giatri"));
                                Ngaychi.setText(obj.getString("Ngaychi"));
                                Ghichu.setText(obj.getString("Ghichu"));
                                Tuai.setText(obj.getString("Denai"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(baocaothuAccount.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("IncomeID",Keys);
                return params;
            }
        };

        requestQueue.add(stringRequest);
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
        getMenuInflater().inflate(R.menu.baocaothu_account, menu);
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
            Intent intent=new Intent(baocaothuAccount.this,Danhmucchi.class);
            startActivity(intent);
        }
        else if(id==R.id.danhsachthu)
        {
            Intent intent=new Intent(baocaothuAccount.this,activity_Danhsachthu.class);
            startActivity(intent);
        }
        else if(id==R.id.trangchu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(baocaothuAccount.this, menu.class);
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
            Intent intent = new Intent(baocaothuAccount.this, baocao.class);
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
            Intent intent = new Intent(baocaothuAccount.this, datetime.class);
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
            Intent intent = new Intent(baocaothuAccount.this, quanlythu.class);
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
            Intent intent = new Intent(baocaothuAccount.this, quanlychi.class);
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
            Intent intent = new Intent(baocaothuAccount.this, taikhoan.class);
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
            Intent intent = new Intent(baocaothuAccount.this, tracutygia.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

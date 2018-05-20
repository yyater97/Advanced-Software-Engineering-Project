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

public class taikhoan extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
            private Dialog dialog,dialogChangeAccount;
    EditText Mataikhoan,Tentaikhoan,Sodu;
    private ListView lvTypeExpenseList;
    private ArrayList<Country> arrayTypeExpense;
    CustomListAdapter adapter;
    EditText Username,Balance,UserChangeAccount,BalanceChangeAccount;
    Button Btn_InsertAccount,Btn_exit,Btn_Thaydoithongtin,Btn_Xemcacgiadich;

    String url = "https://quanpn.000webhostapp.com/manage/selectAccount.php";
    String urlInsert = "https://quanpn.000webhostapp.com/manage/insertAccount.php";
    String urlUdpate = "https://quanpn.000webhostapp.com/manage/updateAccount.php";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Intent a = getIntent();
        Bundle bundle = a.getBundleExtra("getUser");
        final String Keys=bundle.getString("Keys");
        dialog = new Dialog(taikhoan.this);
        dialogChangeAccount=new Dialog(taikhoan.this);
        // khởi tạo dialog
        dialog.setContentView(R.layout.activity_registertaikhoan);
        dialogChangeAccount.setContentView(R.layout.activity_changeaccount);
        Tentaikhoan=(EditText) dialog.findViewById(R.id.txtTentaikhoan) ;
        Username=(EditText) dialog.findViewById(R.id.txtAccount);
        Balance=(EditText) dialog.findViewById(R.id.txtBalance) ;
        Btn_InsertAccount=(Button) dialog.findViewById(R.id.btn_Thaydoithongtin) ;
        Btn_Thaydoithongtin=(Button) dialogChangeAccount.findViewById(R.id.btn_Thaydoithongtin);
        UserChangeAccount=(EditText) dialogChangeAccount.findViewById(R.id.txtTentaikhoan);
        BalanceChangeAccount=(EditText) dialogChangeAccount.findViewById(R.id.txtSodubandau);
        Btn_Xemcacgiadich=(Button) dialogChangeAccount.findViewById(R.id.btn_Xemcacgiaodich);
       final List<Country> image_details = new ArrayList<Country>();
        final ListView listView = (ListView) findViewById(R.id.listView1);
        final List<Country> list=new ArrayList<Country>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                image_details.add(new Country(obj.getString("accountName"),"user",Integer.parseInt(obj.getString("balance"))));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            listView.setAdapter(new CustomListAdapter(taikhoan.this, image_details));
                        }

                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(taikhoan.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                TextView textViewCountry=(TextView) v.findViewById(R.id.textView_countryName) ;
                TextView textViewBalance=(TextView) v.findViewById(R.id.textView_population) ;
               UserChangeAccount.setText(textViewCountry.getText().toString());
                BalanceChangeAccount.setText(Remove(textViewBalance.getText().toString()));
                Btn_Thaydoithongtin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    updateAccount(Keys);
                    }
                });
                Btn_Xemcacgiadich.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("Keys",Keys+"-"+UserChangeAccount.getText().toString());
                        Intent intent = new Intent(taikhoan.this, thuchi.class);
                        intent.putExtra("getUser", bundle);
                        startActivity(intent);
                    }
                });
                dialogChangeAccount.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final FloatingActionButton fab1 = (FloatingActionButton) dialog.findViewById(R.id.btn_Exit);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Btn_InsertAccount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       insertAccount(url,Username.getText().toString(),Keys);
                        RequestQueue requestQueue = Volley.newRequestQueue(taikhoan.this);
                        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                                new Response.Listener<JSONArray>(){
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        for(int i = 0; i<response.length(); i++){
                                            try {
                                                JSONObject obj = response.getJSONObject(i);
                                                list.add(new Country(obj.getString("accountName"),"user",Integer.parseInt(obj.getString("balance"))));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            listView.setAdapter(new CustomListAdapter(taikhoan.this, list));
                                        }

                                    }
                                },
                                new Response.ErrorListener(){

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(taikhoan.this, error.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }
                        );
                        requestQueue.add(jsonArrayRequest);
                    }
                });

                fab1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    private void updateAccount(final String Key){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUdpate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(taikhoan.this,"Thay đổi thành công",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(taikhoan.this,"Thay đổi thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(taikhoan.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("accountID","-ví");
                params.put("accountName","qqqq");
                params.put("balance","qqq");
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    private void addTypeExpense(final String Key){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(taikhoan.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(taikhoan.this,"Thêm thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(taikhoan.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("accountID",Key+"-"+Username.getText().toString().trim());
                params.put("userID",Key);
                params.put("accountName", Username.getText().toString());
                params.put("balance", Balance.getText().toString());
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
    protected void insertAccount(String url, final String a,final String b){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    if((b+"-"+a).equals(obj.getString("accountID")))
                                    {
                                        Toast.makeText(getBaseContext(),""+"Tài khoản đã tồn tại",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        addTypeExpense(b);
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
                        Toast.makeText(taikhoan.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }
    public String Remove(String String)
    {
        String=String.replace("Population: ","");
        String=String.replace(" Đ","");
        return String;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.taikhoan, menu);
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



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

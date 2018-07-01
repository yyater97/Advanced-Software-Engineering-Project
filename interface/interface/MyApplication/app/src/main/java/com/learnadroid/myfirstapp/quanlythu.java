package com.learnadroid.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.learnadroid.myfirstapp.Connect.DictionaryDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class quanlythu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Spinner SpinnerTaikhoan,SpinnerMucchi;
    ConnectionClass connectionClass;
    EditText Giatri,Ngaychi,Ghichu,Tuai;
    Button Luulai;
    private List<String> list ;
    private List<String> list1;
    private String url = "https://quanpn.000webhostapp.com/manage/Laykhoanthu.php";
    private String urlAccount = "https://quanpn.000webhostapp.com/manage/selectAccount.php";
    private String urlInsert = "https://quanpn.000webhostapp.com/manage/insertExpense.php";
    private DictionaryDatabase mDBHelper;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlythu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        mDBHelper=new DictionaryDatabase(this);

        File database=getApplicationContext().getDatabasePath(DictionaryDatabase.DBNAME);
        if(database.exists() == false){
            mDBHelper.getReadableDatabase();
            if(copyDatabase(this)){
                Toast.makeText(getApplicationContext(),"Copy success",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),"Copy failed",Toast.LENGTH_LONG).show();
                return;
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(quanlythu.this, android.R.layout.simple_spinner_item,mDBHelper.SelectAccount(Keys));
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        SpinnerTaikhoan.setAdapter(adapter);
        adapter = new ArrayAdapter(quanlythu.this, android.R.layout.simple_spinner_item,mDBHelper.SelectTypeIncome());
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        SpinnerMucchi.setAdapter(adapter);
        Luulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("".equals(Giatri.getText().toString())||"".equals(Ngaychi.getText().toString()))
                {
                    Toast.makeText(quanlythu.this, "" + "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //InsertExpense(Keys, SpinnerTaikhoan.getSelectedItem().toString(), SpinnerMucchi.getSelectedItem().toString());
                    mDBHelper.insertIncome(Keys + "-" + SpinnerTaikhoan.getSelectedItem().toString(), Giatri.getText().toString(), SpinnerMucchi.getSelectedItem().toString(), Ngaychi.getText().toString(), Ghichu.getText().toString(), Tuai.getText().toString(), Keys);
                    Toast.makeText(quanlythu.this, "" + "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
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
    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    private boolean copyDatabase(Context context){
        try{
            InputStream inputStream=context.getAssets().open(DictionaryDatabase.DBNAME);
            String outFileName=DictionaryDatabase.DBLOCATION + DictionaryDatabase.DBNAME;
            OutputStream outputStream=new FileOutputStream(outFileName);
            byte[] buff=new byte[1024];
            int length=0;
            while ((length=inputStream.read(buff)) >0){
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("Database","Copy Success");
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
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
                            ArrayAdapter<String> adapter = new ArrayAdapter(quanlythu.this, android.R.layout.simple_spinner_item,list);
                            adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                            SpinnerMucchi.setAdapter(adapter);
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(quanlythu.this, error.toString(), Toast.LENGTH_LONG).show();
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
                        ArrayAdapter<String> adapter = new ArrayAdapter(quanlythu.this, android.R.layout.simple_spinner_item,list1);
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
                Toast.makeText(quanlythu.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.quanlythu, menu);
        return true;
    }
    private void InsertExpense(final String Key,final String KeySpinnerTaikhoan,final String KeySpninerMuccchi){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(quanlythu.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(quanlythu.this,"Thêm thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(quanlythu.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
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
                params.put("Denai",Tuai.getText().toString());
                params.put("userID",Key);
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


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.danhsachchi) {
            Intent intent=new Intent(quanlythu.this,Danhmucchi.class);
            startActivity(intent);
        }
        else if(id==R.id.danhsachthu)
        {
            Intent intent=new Intent(quanlythu.this,activity_Danhsachthu.class);
            startActivity(intent);
        }
        else if(id==R.id.trangchu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(quanlythu.this, menu.class);
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
            Intent intent = new Intent(quanlythu.this, baocao.class);
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
            Intent intent = new Intent(quanlythu.this, datetime.class);
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
            Intent intent = new Intent(quanlythu.this, quanlythu.class);
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
            Intent intent = new Intent(quanlythu.this, quanlychi.class);
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
            Intent intent = new Intent(quanlythu.this, taikhoan.class);
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
            Intent intent = new Intent(quanlythu.this, tracutygia.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        else if(id==R.id.setting)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(quanlythu.this, gioithieu.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    protected void LoadKhoanthu(String url) {
    }
}

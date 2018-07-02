package com.learnadroid.myfirstapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

public class Danhmucchi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Dialog dialog,dialogThaydoidanhmuc;
    EditText txtHangmuc,txtDiengiai,txtHanngmucthaydoi,txtDiengiaithaydoi;
    TextView MaHangmuc,Tenhangmuc,Diengiai;
    Button Themhangmuc,Thaydoithongtin,Xoa;
    private DictionaryDatabase mDBHelper;
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
        final List<Danhmucthumua> image_details = new ArrayList<Danhmucthumua>();
        final ListView listView = (ListView) findViewById(R.id.listView1);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Themhangmuc=(Button) dialog.findViewById(R.id.btn_Capnhathongtin);
        txtHanngmucthaydoi=(EditText) dialogThaydoidanhmuc.findViewById(R.id.txtHangmuc);
        txtDiengiaithaydoi=(EditText) dialogThaydoidanhmuc.findViewById(R.id.txtDiengiai);
        Thaydoithongtin=(Button) dialogThaydoidanhmuc.findViewById(R.id.btn_Capnhathongtin);
        Xoa=(Button) dialogThaydoidanhmuc.findViewById(R.id.btn_Thaydoithongtin);
        mDBHelper = new DictionaryDatabase(this);
        File database = getApplicationContext().getDatabasePath(DictionaryDatabase.DBNAME);
        if (database.exists() == false) {
            mDBHelper.getReadableDatabase();
            if (copyDatabase(this)) {
                Toast.makeText(getApplicationContext(), "Copy success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Copy failed", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        listView.setAdapter(new CustomDanhmucmuaApdater(Danhmucchi.this,mDBHelper.selectTypeExpense()));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Themhangmuc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mDBHelper.selectTypeExpenseInsert(txtHangmuc.getText().toString())==true)
                        {
                            Toast.makeText(Danhmucchi.this, "Danh mục đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mDBHelper.insertTypeExpense(txtHangmuc.getText().toString(), Diengiai.getText().toString());
                            listView.setAdapter(new CustomDanhmucmuaApdater(Danhmucchi.this, mDBHelper.selectTypeExpense()));
                            Toast.makeText(Danhmucchi.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });

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

                            mDBHelper.updateTypeExpense(txtHanngmucthaydoi.getText().toString(), txtDiengiaithaydoi.getText().toString(), MaHangmuc.getText().toString());
                            listView.setAdapter(new CustomDanhmucmuaApdater(Danhmucchi.this, mDBHelper.selectTypeExpense()));
                            Toast.makeText(Danhmucchi.this, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                            dialogThaydoidanhmuc.dismiss();

                        }
                });
                Xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDBHelper.deleteTypeExpense(txtHanngmucthaydoi.getText().toString());
                        listView.setAdapter(new CustomDanhmucmuaApdater(Danhmucchi.this, mDBHelper.selectTypeExpense()));
                        Toast.makeText(Danhmucchi.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        dialogThaydoidanhmuc.dismiss();
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

    public String Remove(String String)
    {
        String=String.replace("Hạng mục: ","");
        String=String.replace("Diễn giải: ","");
        return String;
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
        else if(id==R.id.setting)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(Danhmucchi.this, gioithieu.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

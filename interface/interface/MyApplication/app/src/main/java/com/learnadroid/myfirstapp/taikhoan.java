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
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class taikhoan extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
            private Dialog dialog;
    EditText Mataikhoan,Tentaikhoan,Sodu;
    ConnectionClass connectionClass;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent a = getIntent();
        final Bundle Laytendangnhap = a.getBundleExtra("data");
        final String GetTendangnhap=Laytendangnhap.getString("Laytendangnhap");
        dialog = new Dialog(taikhoan.this);
        // khởi tạo dialog
        dialog.setContentView(R.layout.activity_registertaikhoan);
        Mataikhoan=(EditText) dialog.findViewById(R.id.txtMataikhoan) ;
        Tentaikhoan=(EditText) dialog.findViewById(R.id.txtTentaikhoan) ;
        Sodu=(EditText) dialog.findViewById(R.id.txtSodubandau) ;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final List<Country> image_details = getListData();
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(this, image_details));
        // Khi người dùng click vào các ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Country country = (Country) o;
                Toast.makeText(taikhoan.this, "Selected :" + " " + country, Toast.LENGTH_LONG).show();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // xét layout cho dialog
                dialog.setTitle("Đăng kí");
                // xét tiêu đề cho dialog
                Button Buttondangki = (Button) dialog.findViewById(R.id.btnDangKi);
                // khai báo control trong dialog để bắt sự kiện
                Buttondangki.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String z,nm;
                        PreparedStatement stmt = null;
                        connectionClass = new ConnectionClass();
                        try {
                            Connection con = connectionClass.CONN();
                            if (con == null) {
                                z = "Please check your internet connection";
                            } else {
                                String query=" Insert Into account values(?,?,?,?) ";
                                stmt=con.prepareStatement(query);
                                stmt.setString(1, Mataikhoan.getText().toString());
                                stmt.setString(2, GetTendangnhap);
                                stmt.setString(3, Tentaikhoan.getText().toString());
                                stmt.setInt(4, (Integer.parseInt(Sodu.getText().toString())));
                                stmt.executeUpdate();
                            }
                        }
                        catch (Exception ex)
                        {

                            z = "Exceptions"+ex;
                        }
                        List<Country> list = getListData();
                        listView.setAdapter(new CustomListAdapter(taikhoan.this, list));
                    }
                });
                // bắt sự kiện cho nút đăng kí
                Button ButtonHuy=(Button) dialog.findViewById(R.id.btHuy) ;
                ButtonHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }

    private  List<Country> getListData() {
        List<Country> list = new ArrayList<Country>();
        Country vietnam;
        String z;
        connectionClass = new ConnectionClass();
        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Please check your internet connection";
            } else {
                String query=" select  * from account";
                Statement stmt = con.createStatement();
                // stmt.executeUpdate(query);
                ResultSet rs=stmt.executeQuery(query);
                while (rs.next()) {
                    vietnam = new Country(rs.getString(3), "user", rs.getInt(4));
                   list.add(vietnam);
                }

            }
        }
        catch (Exception ex)
        {

            z = "Exceptions"+ex;
        }

        return list;
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

package com.learnadroid.myfirstapp;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class quanlychi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Spinner SpinnerTaikhoan,SpinnerMucchi;
    ConnectionClass connectionClass;
    TextView a;
    EditText Giatri,Ngaychi,Den,Ghichu;
    ImageButton Save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlychi);
        //SpinnerTaikhoan=(Spinner) findViewById(R.id.spTaikhoan);
        SpinnerMucchi=(Spinner) findViewById(R.id.spMucchi);
        a=(TextView) findViewById(R.id.txtMataikhoan) ;
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        Giatri=(EditText) findViewById(R.id.txtGiatri) ;
        Ngaychi=(EditText) findViewById(R.id.txtNgaythu) ;
        Den=(EditText) findViewById(R.id.txtTu) ;
        Ghichu=(EditText) findViewById(R.id.txtGhichu) ;
        Save=(ImageButton) findViewById(R.id.btSave) ;

        String z,nm;

        try {
            connectionClass = new ConnectionClass();
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Please check your internet connection";
            } else {
                String query=" select * from detail_type_income ";
                Statement stmt = con.createStatement();
                // stmt.executeUpdate(query);
                ResultSet rs=stmt.executeQuery(query);
                while (rs.next())
                {
                    list.add(rs.getString(3));

                }
                ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
                adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                SpinnerMucchi.setAdapter(adapter);
                query="select * from account";
                stmt=con.createStatement();
                rs=stmt.executeQuery(query);
                while(rs.next())
                {
                    list1.add(rs.getString(3));

                }
                adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list1);
                adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                SpinnerTaikhoan.setAdapter(adapter);
            }
        }
        catch (Exception ex)
        {

        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a;
                connectionClass = new ConnectionClass();

                try

                {
                    Connection con = connectionClass.CONN();
                    if (con == null) {

                } else {
                    PreparedStatement stmt1 = null;
                    String query=" Insert Into income values(?,?,?,?,?,?,?) ";
                    stmt1=con.prepareStatement(query);
                    stmt1.setString(1,"IC001");
                    stmt1.setString(2,(Giatri.toString()));
                    stmt1.setString(3, "atm");
                    stmt1.setString(4, "Đi chợ/siêu thị");
                    stmt1.setString(5, Ngaychi.toString());
                    stmt1.setString(6, Den.toString());
                    stmt1.setString(7, Ghichu.toString());
                    stmt1.executeUpdate();

                }
            }
        catch (Exception ex)
            {

                a = "Exceptions"+ex;
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

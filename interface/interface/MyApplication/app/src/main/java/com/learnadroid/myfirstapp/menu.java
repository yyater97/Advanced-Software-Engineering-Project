package com.learnadroid.myfirstapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        // Lấy tên đăng nhập
        Intent a = getIntent();
        Bundle bundle = a.getBundleExtra("getUser");
        final String Keys=bundle.getString("Keys");
        // Tạo menu chính
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    {
                        if(finalI==0){
                            Bundle bundle = new Bundle();
                            bundle.putString("Keys",Keys);
                            Intent intent = new Intent(menu.this, taikhoan.class);
                            intent.putExtra("getUser", bundle);
                            startActivity(intent);

                        }
                        if(finalI==1){
                            Bundle bundle = new Bundle();
                            bundle.putString("Keys",Keys);
                            Intent intent = new Intent(menu.this, baocao.class);
                            intent.putExtra("getUser", bundle);
                            startActivity(intent);
                        }
                        if(finalI==2){
                            Bundle bundle = new Bundle();
                            bundle.putString("Keys",Keys);
                            Intent intent = new Intent(menu.this, quanlythu.class);
                            intent.putExtra("getUser", bundle);
                            startActivity(intent);
                        }
                        if(finalI==3){
                            Bundle bundle = new Bundle();
                            bundle.putString("Keys",Keys);
                            Intent intent = new Intent(menu.this, quanlychi.class);
                            intent.putExtra("getUser", bundle);
                            startActivity(intent);
                        }
                        if(finalI==4){
                            Bundle bundle = new Bundle();
                            bundle.putString("Keys",Keys);
                            Intent intent = new Intent(menu.this, tracutygia.class);
                            intent.putExtra("getUser", bundle);
                            startActivity(intent);
                        }
                        if(finalI==5){
                            Bundle bundle = new Bundle();
                            bundle.putString("Keys",Keys);
                            Intent intent = new Intent(menu.this, datetime.class);
                            intent.putExtra("getUser", bundle);
                            startActivity(intent);
                        }
                    }
                }
            });
        }

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
        getMenuInflater().inflate(R.menu.menu, menu);
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
            Intent intent=new Intent(menu.this,Danhmucchi.class);
            startActivity(intent);
        }
        else if(id==R.id.danhsachthu)
        {
            Intent intent=new Intent(menu.this,activity_Danhsachthu.class);
            startActivity(intent);
        }
        else if(id==R.id.trangchu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(menu.this, menu.class);
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
            Intent intent = new Intent(menu.this, baocao.class);
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
            Intent intent = new Intent(menu.this, datetime.class);
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
            Intent intent = new Intent(menu.this, quanlythu.class);
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
            Intent intent = new Intent(menu.this, quanlychi.class);
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
            Intent intent = new Intent(menu.this, taikhoan.class);
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
            Intent intent = new Intent(menu.this, tracutygia.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(menu.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(menu.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    { Intent intent = new Intent(menu.this, quanlythu.class);
                    startActivity(intent);}

                }
            });
        }
    }
}

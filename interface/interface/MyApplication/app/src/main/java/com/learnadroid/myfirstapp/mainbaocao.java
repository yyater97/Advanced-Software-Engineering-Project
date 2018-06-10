package com.learnadroid.myfirstapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mainbaocao extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String urlSelectExpense = "https://quanpn.000webhostapp.com/manage/selectExpenseUserID.php";
    String urlSelectIncome = "https://quanpn.000webhostapp.com/manage/selectIncomeUserID.php";
    TextView textThu,textChi;
    int k=0;int b=0;
    int[] yData = new  int[5];
    int[] xdata=new int[9];
    private Dialog dialog;
    PieChart pieChartThu,pieChartChi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent a = getIntent();
        super.onCreate(savedInstanceState);
        Bundle bundle = a.getBundleExtra("getUser");
        final String Keys=bundle.getString("Keys");
        bundle=a.getBundleExtra("getUser1");
        final String Keys1=bundle.getString("Keys1");
        dialog = new Dialog(mainbaocao.this);
        dialog.setContentView(R.layout.dialogchart);
        pieChartChi=(PieChart) dialog.findViewById(R.id.piechartChi);

        pieChartThu=(PieChart) dialog.findViewById(R.id.piechartThu);
        setContentView(R.layout.activity_mainbaocao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textThu=(TextView) findViewById(R.id.textView14);
        textChi=(TextView) findViewById(R.id.textViewMagiaodich);

        final List<Income> image_details = new ArrayList<Income>();
        final ListView listView = (ListView) findViewById(R.id.listView1);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlSelectExpense, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    for (int i = 0; i < arr.length(); i++) {
                        try {
                            JSONObject obj = arr.getJSONObject(i);
                            {

                                if (Keys.equals(Remove(obj.getString("Ngaychi")))) {

                                    k += Integer.parseInt(obj.getString("Giatri"));
                                    image_details.add(new Income("Thu: " + obj.getString("Mucchi"), "anuong", Integer.parseInt(obj.getString("Giatri")), "Tài khoản: " + obj.getString("accountID"), obj.getInt("ExpenseID")));
                                }
                                    if(Keys.equals(Remove(obj.getString("Ngaychi").substring(0,7))))
                                    {
                                        k += Integer.parseInt(obj.getString("Giatri"));
                                        image_details.add(new Income("Thu: " + obj.getString("Mucchi"), "anuong", Integer.parseInt(obj.getString("Giatri")), "Tài khoản: " + obj.getString("accountID"), obj.getInt("ExpenseID")));
                                    }
                                if(Keys.equals(Remove(obj.getString("Ngaychi").substring(0,4))))
                                {
                                    k += Integer.parseInt(obj.getString("Giatri"));
                                    image_details.add(new Income("Thu: " + obj.getString("Mucchi"), "anuong", Integer.parseInt(obj.getString("Giatri")), "Tài khoản: " + obj.getString("accountID"), obj.getInt("ExpenseID")));
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        textChi.setText(String.valueOf(k));
                        listView.setAdapter(new CustomIncomeApdater(mainbaocao.this,image_details));
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainbaocao.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userID",Keys1);
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
                              {
                                    if (Keys.equals(obj.getString("Ngaychi"))) {

                                        b += Integer.parseInt(obj.getString("Giatri"));
                                        image_details.add(new Income("Chi: " + obj.getString("Mucchi"), "money", Integer.parseInt(obj.getString("Giatri")), "Tài khoản: " + obj.getString("accountID"), obj.getInt("IncomeID")));
                                    }
                                  if (Keys.equals(obj.getString("Ngaychi").substring(0,7))) {

                                      b += Integer.parseInt(obj.getString("Giatri"));
                                      image_details.add(new Income("Chi: " + obj.getString("Mucchi"), "money", Integer.parseInt(obj.getString("Giatri")), "Tài khoản: " + obj.getString("accountID"), obj.getInt("IncomeID")));
                                  }
                                  if (Keys.equals(obj.getString("Ngaychi").substring(0,4))) {

                                      b += Integer.parseInt(obj.getString("Giatri"));
                                      image_details.add(new Income("Chi: " + obj.getString("Mucchi"), "money", Integer.parseInt(obj.getString("Giatri")), "Tài khoản: " + obj.getString("accountID"), obj.getInt("IncomeID")));
                                  }

                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        textThu.setText(String.valueOf(b));
                        listView.setAdapter(new CustomIncomeApdater(mainbaocao.this,image_details));
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainbaocao.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userID",Keys1);
                return params;
            }
        };
        requestQueue.add(stringRequest);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pieChartChi.setRotationEnabled(true);
                pieChartChi.setHoleRadius(35f);
                pieChartChi.setTransparentCircleAlpha(0);
                pieChartChi.setCenterText("CHI");
                pieChartChi.setCenterTextSize(10);
                ChartChi(pieChartThu,Keys,Keys1);
                pieChartThu.setRotationEnabled(true);
                pieChartThu.setDescription(new Description());
                pieChartThu.setHoleRadius(35f);
                pieChartThu.setTransparentCircleAlpha(0);
                pieChartThu.setCenterText("THU");
                pieChartThu.setCenterTextSize(10);

                pieChartThu.setDrawEntryLabels(true);
                
                ChartThu(pieChartChi,Keys,Keys1);
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
        String=String.replace("-0","-");
        return String;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainbaocao, menu);
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

    public void ChartChi(final PieChart pieChart, final String Keys, final String Keys1)
    {
        xdata[0]=0;xdata[1]=0;xdata[2]=0;xdata[3]=0;xdata[4]=0;xdata[5]=0;xdata[6]=0;xdata[7]=0;xdata[8]=0;
        final ArrayList<PieEntry> yEntrys = new ArrayList<>();
        final ArrayList<String> xEntrys = new ArrayList<>();
        final String[] xData = { "January", "February", "January" };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
         StringRequest stringRequest = new StringRequest(Request.Method.POST, urlSelectIncome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    for (int i = 0; i < arr.length(); i++) {
                        try {
                            JSONObject obj = arr.getJSONObject(i);
                            {
                                {
                                    if (Keys.equals(obj.getString("Ngaychi"))) {
                                        {
                                          if("Đi chợ/siêu thị".equals(obj.getString("Mucchi"))||"Quần áo".equals(obj.getString("Mucchi"))||"Giày dép".equals(obj.getString("Mucchi"))||"Đồ điện tử".equals(obj.getString("Mucchi"))||"Phụ kiện khác".equals(obj.getString("Mucchi"))||"Cafe/Trà sữa".equals(obj.getString("Mucchi"))||"Ăn tiệm/cơm quán".equals(obj.getString("Mucchi")))
                                        {
                                            xdata[0]=obj.getInt("Giatri");

                                        }
                                        else if("Học hành - Rèn luyện".equals(obj.getString("Mucchi"))||"Giao lưu - Quan hệ".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[1]+=obj.getInt("Giatri");

                                            }
                                            else if("Điện".equals(obj.getString("Mucchi"))||"Nước".equals(obj.getString("Mucchi"))||"Mạng Internet".equals(obj.getString("Mucchi"))||"Gas/Chất đốt".equals(obj.getString("Mucchi"))||"Truyền hình".equals(obj.getString("Mucchi"))||"Điện thoại".equals(obj.getString("Mucchi")))
                                            {
                                               xdata[2]+=obj.getInt("Giatri");

                                            }
                                            else if("Xăng xe".equals(obj.getString("Mucchi"))||"Bảo hiểm xe".equals(obj.getString("Mucchi"))||"Sửa chữa, bảo dưỡng xe".equals(obj.getString("Mucchi"))||"Gửi xe".equals(obj.getString("Mucchi"))||"Thuê xe/Grab/Taxi/Xe ôm".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[3]+=obj.getInt("Giatri");

                                            }
                                            else if("Vui chơi giải trí".equals(obj.getString("Mucchi"))||"Vật nuôi".equals(obj.getString("Mucchi"))||"Làm đẹp".equals(obj.getString("Mucchi"))||"Du lịch - Phượt".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[4]+=obj.getInt("Giatri");

                                            }
                                            else if("Cưới xin".equals(obj.getString("Mucchi"))||"Ma chay".equals(obj.getString("Mucchi"))||"Thăm hỏi".equals(obj.getString("Mucchi"))||"Biếu tặng".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[5]+=obj.getInt("Giatri");

                                            }
                                            else if("Khám chữa bệnh - Bảo hiểm y tế".equals(obj.getString("Mucchi"))||"Thuốc men".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[6]+=obj.getInt("Giatri");

                                            }
                                            else if("Mua sắm đồ đạc".equals(obj.getString("Mucchi"))||"Sửa chữa nhà cửa".equals(obj.getString("Mucchi"))||"Thuê nhà".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[7]+=obj.getInt("Giatri");

                                            }
                                            else
                                            {
                                                xdata[8]+=obj.getInt("Giatri");

                                            }
                                        }
                                    }
                                    if (Keys.equals(obj.getString("Ngaychi").substring(0,4))) {
                                        {
                                            if("Đi chợ/siêu thị".equals(obj.getString("Mucchi"))||"Quần áo".equals(obj.getString("Mucchi"))||"Giày dép".equals(obj.getString("Mucchi"))||"Đồ điện tử".equals(obj.getString("Mucchi"))||"Phụ kiện khác".equals(obj.getString("Mucchi"))||"Cafe/Trà sữa".equals(obj.getString("Mucchi"))||"Ăn tiệm/cơm quán".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[0]+=obj.getInt("Giatri");

                                            }
                                            else if("Học hành - Rèn luyện".equals(obj.getString("Mucchi"))||"Giao lưu - Quan hệ".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[1]+=obj.getInt("Giatri");

                                            }
                                            else if("Điện".equals(obj.getString("Mucchi"))||"Nước".equals(obj.getString("Mucchi"))||"Mạng Internet".equals(obj.getString("Mucchi"))||"Gas/Chất đốt".equals(obj.getString("Mucchi"))||"Truyền hình".equals(obj.getString("Mucchi"))||"Điện thoại".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[2]+=obj.getInt("Giatri");

                                            }
                                            else if("Xăng xe".equals(obj.getString("Mucchi"))||"Bảo hiểm xe".equals(obj.getString("Mucchi"))||"Sửa chữa, bảo dưỡng xe".equals(obj.getString("Mucchi"))||"Gửi xe".equals(obj.getString("Mucchi"))||"Thuê xe/Grab/Taxi/Xe ôm".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[3]+=obj.getInt("Giatri");

                                            }
                                            else if("Vui chơi giải trí".equals(obj.getString("Mucchi"))||"Vật nuôi".equals(obj.getString("Mucchi"))||"Làm đẹp".equals(obj.getString("Mucchi"))||"Du lịch - Phượt".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[4]+=obj.getInt("Giatri");

                                            }
                                            else if("Cưới xin".equals(obj.getString("Mucchi"))||"Ma chay".equals(obj.getString("Mucchi"))||"Thăm hỏi".equals(obj.getString("Mucchi"))||"Biếu tặng".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[5]+=obj.getInt("Giatri");

                                            }
                                            else if("Khám chữa bệnh - Bảo hiểm y tế".equals(obj.getString("Mucchi"))||"Thuốc men".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[6]+=obj.getInt("Giatri");

                                            }
                                            else if("Mua sắm đồ đạc".equals(obj.getString("Mucchi"))||"Sửa chữa nhà cửa".equals(obj.getString("Mucchi"))||"Thuê nhà".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[7]+=obj.getInt("Giatri");

                                            }
                                            else
                                            {
                                                xdata[8]+=obj.getInt("Giatri");

                                            }
                                        }
                                    }
                                    if (Keys.equals(obj.getString("Ngaychi").substring(0,7))) {
                                        {
                                            if("Đi chợ/siêu thị".equals(obj.getString("Mucchi"))||"Quần áo".equals(obj.getString("Mucchi"))||"Giày dép".equals(obj.getString("Mucchi"))||"Đồ điện tử".equals(obj.getString("Mucchi"))||"Phụ kiện khác".equals(obj.getString("Mucchi"))||"Cafe/Trà sữa".equals(obj.getString("Mucchi"))||"Ăn tiệm/cơm quán".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[0]+=obj.getInt("Giatri");
                                            }
                                            else if("Học hành - Rèn luyện".equals(obj.getString("Mucchi"))||"Giao lưu - Quan hệ".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[1]+=obj.getInt("Giatri");
                                            }
                                          else if("Điện".equals(obj.getString("Mucchi"))||"Nước".equals(obj.getString("Mucchi"))||"Mạng Internet".equals(obj.getString("Mucchi"))||"Gas/Chất đốt".equals(obj.getString("Mucchi"))||"Truyền hình".equals(obj.getString("Mucchi"))||"Điện thoại".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[2]+=obj.getInt("Giatri");
                                            }
                                            else if("Xăng xe".equals(obj.getString("Mucchi"))||"Bảo hiểm xe".equals(obj.getString("Mucchi"))||"Sửa chữa, bảo dưỡng xe".equals(obj.getString("Mucchi"))||"Gửi xe".equals(obj.getString("Mucchi"))||"Thuê xe/Grab/Taxi/Xe ôm".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[3]+=obj.getInt("Giatri");
                                            }
                                            else if("Vui chơi giải trí".equals(obj.getString("Mucchi"))||"Vật nuôi".equals(obj.getString("Mucchi"))||"Làm đẹp".equals(obj.getString("Mucchi"))||"Du lịch - Phượt".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[4]+=obj.getInt("Giatri");
                                            }
                                            if("Cưới xin".equals(obj.getString("Mucchi"))||"Ma chay".equals(obj.getString("Mucchi"))||"Thăm hỏi".equals(obj.getString("Mucchi"))||"Biếu tặng".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[5]+=obj.getInt("Giatri");
                                            }
                                            else if("Khám chữa bệnh - Bảo hiểm y tế".equals(obj.getString("Mucchi"))||"Thuốc men".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[6]+=obj.getInt("Giatri");
                                            }
                                            else if("Mua sắm đồ đạc".equals(obj.getString("Mucchi"))||"Sửa chữa nhà cửa".equals(obj.getString("Mucchi"))||"Thuê nhà".equals(obj.getString("Mucchi")))
                                            {
                                                xdata[7]+=obj.getInt("Giatri");
                                            }
                                            else
                                            {
                                                xdata[8]+=obj.getInt("Giatri");

                                            }

                                        }
                                    }
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    for (int i = 0; i < xdata.length;i++){
                        yEntrys.add(new PieEntry(xdata[i],i));
                    }
                    for (int i = 0; i < xData.length;i++){
                        xEntrys.add(xData[i]);
                    }
                    PieDataSet pieDataSet=new PieDataSet(yEntrys,"");
                    pieDataSet.setSliceSpace(2);
                    pieDataSet.setValueTextSize(12);
                    ArrayList<Integer> colors=new ArrayList<>();
                    colors.add(Color.RED);
                    colors.add(Color.GRAY);
                    colors.add(Color.parseColor("#FF6600"));
                    colors.add(Color.parseColor("#00FFFF"));
                    colors.add(Color.parseColor("#00CC33"));
                    colors.add(Color.parseColor("#CC33FF"));
                    colors.add(Color.parseColor("#FFFF33"));
                    colors.add(Color.BLUE);
                    colors.add(Color.parseColor("#FF6699"));

                    pieDataSet.setColors(colors);
                    Legend legend=pieChart.getLegend();
                    legend.setForm(Legend.LegendForm.CIRCLE);
                    legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
                    PieData pieData=new PieData(pieDataSet);
                    pieChart.setData(pieData);
                    pieChart.invalidate();
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainbaocao.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userID",Keys1);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void ChartThu(final PieChart pieChart, final String Keys, final String Keys1)
    {
        yData[0]=0;yData[1]=0;yData[2]=0;yData[3]=0;yData[4]=0;
        final ArrayList<PieEntry> yEntrys = new ArrayList<>();
        final ArrayList<String> xEntrys = new ArrayList<>();
        final String[] xData = { "January", "February", "January" };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlSelectExpense, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    for (int i = 0; i < arr.length(); i++) {
                        try {
                            JSONObject obj = arr.getJSONObject(i);
                            {
                                {
                                    if(Keys.equals(obj.getString("Ngaychi"))) {
                                    if ("Lương".equals(obj.getString("Mucchi"))) {

                                        yData[0] = +obj.getInt("Giatri");
                                    } else if ("Được cho/tặng".equals(obj.getString("Mucchi"))) {

                                        yData[1] = +obj.getInt("Giatri");
                                    } else if ( "Lãi tiết kiệm".equals(obj.getString("Mucchi"))) {
                                        yData[2] = +obj.getInt("Giatri");
                                    } else if ( "Thưởng".equals(obj.getString("Mucchi"))) {

                                        yData[3] = +obj.getInt("Giatri");
                                    } else  {
                                        yData[4] = +obj.getInt("Giatri");
                                    }
                                }
                                    if(Keys.equals(obj.getString("Ngaychi").substring(0,4))) {
                                        if ("Lương".equals(obj.getString("Mucchi"))) {

                                            yData[0] = +obj.getInt("Giatri");
                                        } else if ("Được cho/tặng".equals(obj.getString("Mucchi"))) {

                                            yData[1] = +obj.getInt("Giatri");
                                        } else if ( "Lãi tiết kiệm".equals(obj.getString("Mucchi"))) {
                                            yData[2] = +obj.getInt("Giatri");
                                        } else if ( "Thưởng".equals(obj.getString("Mucchi"))) {

                                            yData[3] = +obj.getInt("Giatri");
                                        } else  {
                                            yData[4] = +obj.getInt("Giatri");
                                        }
                                    }
                                    if(Keys.equals(obj.getString("Ngaychi").substring(0,7))) {
                                        if ("Lương".equals(obj.getString("Mucchi"))) {

                                            yData[0] = +obj.getInt("Giatri");
                                        } else if ("Được cho/tặng".equals(obj.getString("Mucchi"))) {

                                            yData[1] = +obj.getInt("Giatri");
                                        } else if ( "Lãi tiết kiệm".equals(obj.getString("Mucchi"))) {
                                            yData[2] = +obj.getInt("Giatri");
                                        } else if ( "Thưởng".equals(obj.getString("Mucchi"))) {

                                            yData[3] = +obj.getInt("Giatri");
                                        } else  {
                                            yData[4] = +obj.getInt("Giatri");
                                        }
                                    }
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < yData.length;i++){
                        yEntrys.add(new PieEntry(yData[i],i));
                    }
                    for (int i = 0; i < xData.length;i++){
                        xEntrys.add(xData[i]);
                    }

                    PieDataSet pieDataSet=new PieDataSet(yEntrys,"");
                    pieDataSet.setSliceSpace(2);
                    pieDataSet.setValueTextSize(12);
                    ArrayList<Integer> colors=new ArrayList<>();
                    colors.add(Color.RED);
                    colors.add(Color.GRAY);
                    colors.add(Color.YELLOW);
                    colors.add(Color.BLUE);
                    colors.add(Color.GREEN);

                    pieDataSet.setColors(colors);
                    Legend legend=pieChart.getLegend();
                    legend.setForm(Legend.LegendForm.CIRCLE);
                    legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
                    PieData pieData=new PieData(pieDataSet);
                    pieChart.setData(pieData);
                    pieChart.invalidate();
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainbaocao.this,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userID",Keys1);
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

        if (id == R.id.danhsachchi) {
            Intent intent=new Intent(mainbaocao.this,Danhmucchi.class);
            startActivity(intent);
        }
        else if(id==R.id.danhsachthu)
        {
            Intent intent=new Intent(mainbaocao.this,activity_Danhsachthu.class);
            startActivity(intent);
        }
        else if(id==R.id.trangchu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(mainbaocao.this, menu.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        else if(id==R.id.Baocao)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(mainbaocao.this, baocao.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        else if(id==R.id.lich)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(mainbaocao.this, datetime.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        else if(id==R.id.quanlythu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(mainbaocao.this, quanlythu.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        else if(id==R.id.quanlychi)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(mainbaocao.this, quanlychi.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        else if(id==R.id.cactaikhoan)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(mainbaocao.this, taikhoan.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        else if(id==R.id.tracuu)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser1");
            final String Keys=bundle.getString("Keys1");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(mainbaocao.this, tracutygia.class);
            intent.putExtra("getUser", bundle);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

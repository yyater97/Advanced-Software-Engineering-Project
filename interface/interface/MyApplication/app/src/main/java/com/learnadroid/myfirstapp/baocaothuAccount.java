package com.learnadroid.myfirstapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

import static com.android.volley.Response.ErrorListener;

public class baocaothuAccount extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Dialog dialog,dialogdatetime;
    EditText Giatri,Ngaychi,Ghichu,Tuai;
    Spinner SpinnerTaikhoan,SpinnerMucchi;
    TextView textViewMaGD,textViewCohieu;
    Button Xoa,Luulai;
    String urlInsert = "https://quanpn.000webhostapp.com/manage/select.php";
    String urlSelectIncome = "https://quanpn.000webhostapp.com/manage/selectIncome.php";
    String urlSelectExpense = "https://quanpn.000webhostapp.com/manage/select.php";
    private String url = "https://quanpn.000webhostapp.com/manage/Laykhoanthu.php";
    private String url1 = "https://quanpn.000webhostapp.com/manage/selectKhoanchi.php";
    private String urlAccount = "https://quanpn.000webhostapp.com/manage/selectAccount.php";
    private List<String> list ;
    private List<String> list1;
    DatePicker dp;
    private  DictionaryDatabase mDBHelper;
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
        dialogdatetime=new Dialog(baocaothuAccount.this);
        dialogdatetime.setContentView(R.layout.datetime);
        SpinnerTaikhoan=(Spinner) dialog.findViewById(R.id.sp_Taikhoan);
        SpinnerMucchi=(Spinner) dialog.findViewById(R.id.sp_Mucchi);
        Giatri=(EditText) dialog.findViewById(R.id.txt_Giatri);
        Ngaychi=(EditText) dialog.findViewById(R.id.txt_Ngaychi);
        Ghichu=(EditText) dialog.findViewById(R.id.txt_Ghichu);
        Tuai=(EditText) dialog.findViewById(R.id.txt_Denai);
        mDBHelper = new DictionaryDatabase(this);
        Xoa=(Button)dialog.findViewById(R.id.btn_Xoa);
        Luulai=(Button)dialog.findViewById(R.id.btn_Luulai);
        dp=(DatePicker) dialogdatetime.findViewById(R.id.datePicker);
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
         final List<Income> image_details = new ArrayList<Income>();

        final ListView listView = (ListView) findViewById(R.id.listView1);

        for(int i=0;i<mDBHelper.SelectExpenseAccountID(Keys).size();i++)
        {
            image_details.add(mDBHelper.SelectExpenseAccountID(Keys).get(i));
        }
        for(int i=0;i<mDBHelper.SelectIncomeAccountID(Keys).size();i++)
        {
            image_details.add(mDBHelper.SelectIncomeAccountID(Keys).get(i));
        }
        FloatingActionButton fab = (FloatingActionButton) dialog.findViewById(R.id.btn_datetime);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingActionButton btn_OK = (FloatingActionButton) dialogdatetime.findViewById(R.id.btn_OK);

                btn_OK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        {
                            int	day		=	 dp.getDayOfMonth();
                            int month	=	dp.getMonth()+1;
                            int	year	=	dp.getYear();
                            Ngaychi.setText(day+"/"+month+"/"+year);
                            dialogdatetime.dismiss();

                        }

                    }
                });
                FloatingActionButton btn_Huy = (FloatingActionButton) dialogdatetime.findViewById(R.id.btn_Huy);

                btn_Huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        {
                            dialogdatetime.dismiss();
                        }

                    }
                });
                dialogdatetime.show();
            }
        });

        listView.setAdapter(new CustomIncomeApdater(baocaothuAccount.this,image_details));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                textViewMaGD=(TextView) v.findViewById(R.id.textViewMagiaodich);
                textViewCohieu=(TextView) v.findViewById(R.id.textView_countryName);
                ArrayAdapter<String> adapter = new ArrayAdapter(baocaothuAccount.this, android.R.layout.simple_spinner_item,mDBHelper.SelectAccount(Keys1));
                adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                SpinnerTaikhoan.setAdapter(adapter);
               if(textViewCohieu.getText().toString().startsWith("C")==true)
               {
                   Giatri.setText(mDBHelper.selectExpense(Remove(textViewMaGD.getText().toString())).getGiattri());
                   Ngaychi.setText(mDBHelper.selectExpense(Remove(textViewMaGD.getText().toString())).getNgaychi());
                   Ghichu.setText(mDBHelper.selectExpense(Remove(textViewMaGD.getText().toString())).getGhichu());
                   Tuai.setText(mDBHelper.selectExpense(Remove(textViewMaGD.getText().toString())).getDenai());
                   adapter = new ArrayAdapter(baocaothuAccount.this, android.R.layout.simple_spinner_item,mDBHelper.SelectTypeExpense());
                   adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                   SpinnerMucchi.setAdapter(adapter);
                   SpinnerMucchi.setAdapter(adapter);
               }
                if(textViewCohieu.getText().toString().startsWith("T")==true)
                {
                   Giatri.setText(mDBHelper.selectIncome(Remove(textViewMaGD.getText().toString())).getGiattri());
                   Ngaychi.setText(mDBHelper.selectIncome(Remove(textViewMaGD.getText().toString())).getNgaychi());
                    Ghichu.setText(mDBHelper.selectIncome(Remove(textViewMaGD.getText().toString())).getGhichu());
                    Tuai.setText(mDBHelper.selectIncome(Remove(textViewMaGD.getText().toString())).getDenai());
                    adapter = new ArrayAdapter(baocaothuAccount.this, android.R.layout.simple_spinner_item,mDBHelper.SelectTypeIncome());
                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    SpinnerMucchi.setAdapter(adapter);
                }
                Xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isConnected()==true) {

                            mDBHelper.deleteExpense(Remove(textViewMaGD.getText().toString()));
                            mDBHelper.deleteIncome(Remove(textViewMaGD.getText().toString()));
                            deleteIncome(baocaothuAccount.this,Remove(textViewMaGD.getText().toString())+"("+Keys1+")");
                            deleteExpense(baocaothuAccount.this,Remove(textViewMaGD.getText().toString())+"("+Keys1+")");
                            Toast.makeText(baocaothuAccount.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            listView.setAdapter(null);
                            List<Income> image_details1 = new ArrayList<Income>();
                            for(int i=0;i<mDBHelper.SelectExpenseAccountID(Keys).size();i++)
                            {
                                image_details1.add(mDBHelper.SelectExpenseAccountID(Keys).get(i));
                            }
                            for(int i=0;i<mDBHelper.SelectIncomeAccountID(Keys).size();i++)
                            {
                                image_details1.add(mDBHelper.SelectIncomeAccountID(Keys).get(i));
                            }
                            listView.setAdapter(new CustomIncomeApdater(baocaothuAccount.this,image_details1));
                        }
                        else {
                            Toast.makeText(baocaothuAccount.this, "Cần kết nối internet để thực hiện chức năng này", Toast.LENGTH_SHORT).show();

                        }
                        dialog.dismiss();
                    }
                });
                Luulai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(baocaothuAccount.this, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                       mDBHelper.UpdateExpense(Remove(textViewMaGD.getText().toString()),Keys1+"-"+SpinnerTaikhoan.getSelectedItem().toString(),Giatri.getText().toString(),SpinnerMucchi.getSelectedItem().toString(),subString(Ngaychi.getText().toString(),2)+"-"+subString(Ngaychi.getText().toString(),1)+"-"+subString(Ngaychi.getText().toString(),0),Ghichu.getText().toString(),Tuai.getText().toString(),Keys1);
                        mDBHelper.UpdateIncome(Remove(textViewMaGD.getText().toString()),Keys1+"-"+SpinnerTaikhoan.getSelectedItem().toString(),Giatri.getText().toString(),SpinnerMucchi.getSelectedItem().toString(),subString(Ngaychi.getText().toString(),2)+"-"+subString(Ngaychi.getText().toString(),1)+"-"+subString(Ngaychi.getText().toString(),0),Ghichu.getText().toString(),Tuai.getText().toString(),Keys1);
                        listView.setAdapter(null);
                        List<Income> image_details1 = new ArrayList<Income>();
                        for(int i=0;i<mDBHelper.SelectExpenseAccountID(Keys).size();i++)
                        {
                            image_details1.add(mDBHelper.SelectExpenseAccountID(Keys).get(i));
                        }
                        for(int i=0;i<mDBHelper.SelectIncomeAccountID(Keys).size();i++)
                        {
                            image_details1.add(mDBHelper.SelectIncomeAccountID(Keys).get(i));
                        }
                        listView.setAdapter(new CustomIncomeApdater(baocaothuAccount.this,image_details1));
                        dialog.dismiss();
                    }
                });
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

    private void deleteIncome(final Context context, final String ExpenseID){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/deleteIncome.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(context,"Xóa thành công",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(context,"Xóa thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Xảy ra lỗi!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("IncomeID",ExpenseID);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    private void deleteExpense(final Context context, final String ExpenseID){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/deleteExpense.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                    Toast.makeText(context,"Xóa thành công",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(context,"Xóa thất bại!!!",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("ExpenseID",ExpenseID);
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
    public String Remove(String String)
    {
        String=String.replace("Mã giao dịch: ","");

        return String;
    }
    public String subString(String String,int k )
    {
        String a=null;
        String[] strArr;
        strArr =String.split("/");
        for(int i = 0; i < strArr.length; i++){
            a=(strArr[k]);
        }
        return  a;
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
        else if(id==R.id.setting)
        {
            Intent a = getIntent();
            Bundle bundle = a.getBundleExtra("getUser");
            final String Keys=bundle.getString("Keys");
            Bundle bundle1 = new Bundle();
            bundle1.putString("Keys",Keys);
            Intent intent = new Intent(baocaothuAccount.this, gioithieu.class);
            intent.putExtra("getUser", bundle1);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

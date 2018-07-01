package com.learnadroid.myfirstapp;
import com.learnadroid.myfirstapp.Connect.*;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity  {

    EditText Username,Password;
    TextView TextView,txt;
    Button SingIn,SingUp;
    private String url = "https://quanpn.000webhostapp.com/manage/user.php";
    private DictionaryDatabase mDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        SingIn=(Button) findViewById(R.id.btn_Thaydoithongtin);
        SingUp=(Button) findViewById(R.id.btn_Luulai);
       Username=(EditText) findViewById(R.id.txtUsername);
       Password=(EditText) findViewById(R.id.txtPassword);
        TextView =(TextView) findViewById(R.id.txtLogin);
        txt =(TextView) findViewById(R.id.txt);
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
       SingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(mDBHelper.Dangnhap(Username.getText().toString(), Password.getText().toString())==true)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putString("Keys",Username.getText().toString().trim());
                        Intent intent = new Intent(MainActivity.this, menu.class);
                        intent.putExtra("getUser", bundle);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getBaseContext(),""+"Thông tin tài khoản hoặc mật khẩu không chính xác",Toast.LENGTH_LONG).show();
                    }

            }
        });
        SingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Bundle mm = new Bundle();
                mm.putString("mmm","c");
                Intent c = new Intent(MainActivity.this, register.class);
                c.putExtra("mmxfhhh", mm);
                startActivity(c);
            }
        });

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
    protected void DangNhap(String url, final String a,final String b){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                {
                                    if(a.equals(obj.getString("userName"))&&b.equals(obj.getString("password")))
                                    {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Keys",Username.getText().toString().trim());
                                        Intent intent = new Intent(MainActivity.this, menu.class);
                                        intent.putExtra("getUser", bundle);
                                        startActivity(intent);
                                        break;
                                    }
                                    if(obj.getString("userName") != a&&obj.getString("password")!=b){
                                            txt.setText("ĐĂNG NHẬP THẤT BẠI");
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
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

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

}

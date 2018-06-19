package com.learnadroid.myfirstapp.Connect;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.learnadroid.myfirstapp.Country;
import com.learnadroid.myfirstapp.menu;
import com.learnadroid.myfirstapp.register;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryDatabase  extends SQLiteOpenHelper {
    private String urlInsert="https://quanpn.000webhostapp.com/manage/InsertUser.php";
    private String url = "https://quanpn.000webhostapp.com/manage/user.php";
    public static final String DBNAME="a.db";
    public static final String DBLOCATION="/data/data/com.learnadroid.myfirstapp/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public DictionaryDatabase(Context context){
        super(context,DBNAME,null,1);
        this.mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDatabase(){
        String dbPath=mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase!=null && mDatabase.isOpen()){
            return;
        }
        mDatabase=SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public void closeDatabase(){
        if(mDatabase !=null){
            mDatabase.close();
        }
    }

    public boolean Dangnhap(String Tentaikhoan,String Matkhau){
        openDatabase();
        String query = "Select * from user";
        Cursor cursor = mDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                String userID = cursor.getString(cursor.getColumnIndexOrThrow("userName"));
                String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                if(Tentaikhoan.equals(userID) && Matkhau.equals(password) )
                {
                    return true;
                }

            }while(cursor.moveToNext());

        }
        cursor.close();
        return false;
    }
    public boolean DangnhapInsertUser(String Tentaikhoan){
        openDatabase();
        String query = "Select * from user";
        Cursor cursor = mDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                String userID = cursor.getString(cursor.getColumnIndexOrThrow("userName"));

                if(Tentaikhoan.equals(userID) )
                {
                    return true;
                }

            }while(cursor.moveToNext());

        }
        cursor.close();
        return false;
    }
    public List<Country> Taikhoan(String userID)
    {
        List<Country> list = new ArrayList<Country>();
        openDatabase();

        String query = "Select * from account";
        Cursor cursor = mDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                if(userID.equals(cursor.getString(cursor.getColumnIndexOrThrow("userID"))) )
                {
                    list.add(new Country(cursor.getString(cursor.getColumnIndexOrThrow("accountName")),"user",Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("balance")))));
                }

            }while(cursor.moveToNext());

        }
        cursor.close();
        return list;
    }
    public void insertUser(String userID,String userName,String Email,String password) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put("userID", userID);
        values.put("userName", userName);
        values.put("password", password);
        values.put("Email", Email);
        mDatabase.insert("user", null, values);
        closeDatabase();
    }
    public void insertAccount(String accountID,String userID,String accountName,String balance) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put("accountID", accountID);
        values.put("userID", userID);
        values.put("accountName", accountName);
        values.put("balance",balance);
        mDatabase.insert("account", null, values);
        closeDatabase();
    }
    public void addTypeExpense(final Context context,final String userName,final String Email,final String password){
        final RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if((response.trim()).equals("success")){


                }else{
                    Toast.makeText(context,"Thêm thất bại!!!",Toast.LENGTH_LONG).show();
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
                params.put("userID",userName);
                params.put("userName",userName);
                params.put("password", password);
                params.put("Email", Email);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void InsertUserMenu(final Context context){
        openDatabase();
        String query = "Select * from user";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                final String userID = cursor.getString(cursor.getColumnIndexOrThrow("userName"));
                final String Email = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
                final String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                final RequestQueue requestQueue = Volley.newRequestQueue(context);
                final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>(){
                            @Override
                            public void onResponse(JSONArray response) {
                                for(int i = 0; i<response.length(); i++){
                                    try {
                                        JSONObject obj = response.getJSONObject(i);
                                        {
                                            if(!obj.getString("userName").equals(userID))
                                            {
                                                addTypeExpense(context,userID,Email,password);
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
                                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                requestQueue.add(jsonArrayRequest);

            }while(cursor.moveToNext());

        }
        cursor.close();

    }

}

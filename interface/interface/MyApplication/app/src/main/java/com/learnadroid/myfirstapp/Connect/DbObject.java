package com.learnadroid.myfirstapp.Connect;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DbObject {

    private static DictionaryDatabase dbHelper;
    private SQLiteDatabase db;

    public DbObject(Context context) {
        dbHelper = new DictionaryDatabase(context);
        this.db = dbHelper.getReadableDatabase();
    }

    public SQLiteDatabase getDbConnection(){
        return this.db;
    }

    public void closeDbConnection(){
        if(this.db != null){
            this.db.close();
        }
    }
    /* private void deleteAccountService(final Context context, final String accountID){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/deleteAccount.php", new Response.Listener<String>() {
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
                Toast.makeText(context,"Xảy ra lỗi 5555!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("accountID",accountID);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }*/
    /*public void DeleteAccountMenu(final Context context,final String Keys){
        openDatabase();
        String query = "Select * from account";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{

                final String userID = cursor.getString(cursor.getColumnIndexOrThrow("userID"));
                final String accountID = cursor.getString(cursor.getColumnIndexOrThrow("accountID"));
                final RequestQueue requestQueue = Volley.newRequestQueue(context);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/selectAccount.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray arr = new JSONArray(response);
                            for (int i = 0; i < arr.length(); i++) {
                                try {
                                    JSONObject obj = arr.getJSONObject(i);
                                    {
                                        if(!accountID.equals(obj.getString("accountID")))
                                        {


                                        }

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
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
                        params.put("userID",Keys);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);

            }while(cursor.moveToNext());

        }
        cursor.close();

    }
    private void deleteExpenseService(final Context context, final String ExpenseID){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/deleteExpenseAccountID.php", new Response.Listener<String>() {
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
                params.put("accountID",ExpenseID);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    private void deleteIncomeService(final Context context, final String ExpenseID){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/deleteIncomeAccountID.php", new Response.Listener<String>() {
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
                params.put("accountID",ExpenseID);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }*/
}

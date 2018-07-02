package com.learnadroid.myfirstapp.Connect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
import com.learnadroid.myfirstapp.Danhmucthumua;
import com.learnadroid.myfirstapp.Income;
import com.learnadroid.myfirstapp.quanlychi;

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
    public static final String DBNAME="manage1.db";
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
    public List<Danhmucthumua> selectTypeIncome(){
        openDatabase();
        List<Danhmucthumua> image_details = new ArrayList<Danhmucthumua>();
        String query = "Select * from type_income";
        Cursor cursor = mDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                String type_incomeName = cursor.getString(cursor.getColumnIndexOrThrow("type_incomeName"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String type_incomeID = cursor.getString(cursor.getColumnIndexOrThrow("type_incomeID"));
                if("Lương".equals(type_incomeName)) {
                    image_details.add(new Danhmucthumua(type_incomeName, "tienluong", description,type_incomeID));
                }
                else if(type_incomeName.equals("Thưởng")) {
                    image_details.add(new Danhmucthumua(type_incomeName, "thuong", description,type_incomeID));
                }
                else if(type_incomeName.equals("Được cho/tặng")) {
                    image_details.add(new Danhmucthumua(type_incomeName, "lixi", description,type_incomeID));
                }
                else if(type_incomeName.equals("Lãi tiết kiệm")) {
                    image_details.add(new Danhmucthumua(type_incomeName, "tienlai", description,type_incomeID));
                }
                else  {
                    image_details.add(new Danhmucthumua(type_incomeName, "thukhac", description,type_incomeID));
                }

            }while(cursor.moveToNext());

        }
        cursor.close();
        return image_details;
    }
    public List<Danhmucthumua> selectTypeExpense(){
        openDatabase();
        List<Danhmucthumua> image_details = new ArrayList<Danhmucthumua>();
        String query = "Select * from detail_type_expense";
        Cursor cursor = mDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                String detail_type_expenseName = cursor.getString(cursor.getColumnIndexOrThrow("detail_type_expenseName"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String detail_type_expenseID = cursor.getString(cursor.getColumnIndexOrThrow("detail_type_expenseID"));
                if("Đi chợ/siêu thị".equals(detail_type_expenseName)) {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "dicho", description,detail_type_expenseID));
                }
                else if ("Xăng xe".equals(detail_type_expenseName)) {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "xangxe", description,detail_type_expenseID));
                }

                else if ("Bảo hiểm xe".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "hocphi", description,detail_type_expenseID));
                }
                else if ("Sửa chữa, bảo dưỡng xe".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "suaxe", description,detail_type_expenseID));
                }
                else if ("Gửi xe".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "baohiem", description,detail_type_expenseID));
                }
                else if ("Thuê xe/Grab/Taxi/Xe ôm".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "hocphi", description,detail_type_expenseID));
                }
                else if ("Học phí".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "money", description,detail_type_expenseID));
                }
                else if ("Sách vở - Đồ dùng học tập".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "sachvo", description,detail_type_expenseID));
                }
                else if ("Sữa".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "sua", description,detail_type_expenseID));
                }
                else if ("Đồ chơi".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "dochoi", description,detail_type_expenseID));
                }
                else if ("Tiền tiêu vặt".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "money", description,detail_type_expenseID));
                }
                else if ("Ăn tiệm/cơm quán".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "foot", description,detail_type_expenseID));
                }
                else if ("Quần áo".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "ao", description,detail_type_expenseID));
                }
                else if ("Giầy dép".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "giay", description,detail_type_expenseID));
                }
                else if ("Đồ điện tử".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "uong", description,detail_type_expenseID));
                }
                else if ("Phụ kiện khác".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "quan", description,detail_type_expenseID));
                }
                else if ("Cưới xin".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "damcuoi", description,detail_type_expenseID));
                }
                else if ("Khám chữa bệnh - Bảo hiểm y tế".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "baohiem", description,detail_type_expenseID));
                }
                else if ("Thăm hỏi".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "qua", description,detail_type_expenseID));
                }
                else if ("Biếu tặng".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "qua", description,detail_type_expenseID));
                }
                else if ("Cafe/Trà sữa".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "cafe", description,detail_type_expenseID));
                }
                else if ("Sửa chữa nhà cửa".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "thuenha", description,detail_type_expenseID));
                }
                else if ("Mua sắm đồ đạc".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "ao1", description,detail_type_expenseID));
                }
                else if ("Du lịch - Phượt".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "dulich", description,detail_type_expenseID));
                }
                else if ("Truyền hình".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "truyenhinh", description,detail_type_expenseID));
                }
                else if ("Mạng Internet".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "internet", description,detail_type_expenseID));
                }
                else if ("Điện thoại".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "dienthoai", description,detail_type_expenseID));
                }
                else if ("Điện".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "dien", description,detail_type_expenseID));
                }
                else if ("Giao lưu - Quan hệ".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "giaoluu", description,detail_type_expenseID));
                }
                else if ("Vật nuôi".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "vatnuoi", description,detail_type_expenseID));
                }
                else if ("Học hành - Rèn luyện".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "hoctap", description,detail_type_expenseID));
                }
                else if ("Làm đẹp".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "lamdep", description,detail_type_expenseID));
                }
                else if ("Thuê nhà".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "thuenha", description,detail_type_expenseID));
                }
                else if ("Thuốc men".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "thuoc", description,detail_type_expenseID));
                }
                else if ("Gas/Chất đốt".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "gas", description,detail_type_expenseID));
                }
                else if ("Nước".equals(detail_type_expenseName))
                {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "nuoc", description,detail_type_expenseID));
                }
                else {
                    image_details.add(new Danhmucthumua(detail_type_expenseName, "dulich", description,detail_type_expenseID));
                }

            }while(cursor.moveToNext());

        }
        cursor.close();
        return image_details;
    }
    public boolean selectTypeExpenseInsert(String Tentaikhoan){
        openDatabase();
        String query = "Select * from detail_type_expense";
        Cursor cursor = mDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                String userID = cursor.getString(cursor.getColumnIndexOrThrow("detail_type_expenseName"));

                if(Tentaikhoan.equals(userID) )
                {
                    return true;
                }

            }while(cursor.moveToNext());

        }
        cursor.close();
        return false;
    }
    public boolean selectTypeIncomeInsert(String Tentaikhoan){
        openDatabase();
        String query = "Select * from type_income";
        Cursor cursor = mDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                String userID = cursor.getString(cursor.getColumnIndexOrThrow("type_incomeName"));

                if(Tentaikhoan.equals(userID) )
                {
                    return true;
                }

            }while(cursor.moveToNext());

        }
        cursor.close();
        return false;
    }
    public void deleteTypeExpense(String detail_type_expenseName) {
        openDatabase();
        mDatabase.delete("detail_type_expense", "detail_type_expenseName"+"=?",new String[]{detail_type_expenseName});
        closeDatabase();
    }
    public void deleteTypeIncome(String type_incomeName) {
        openDatabase();
        mDatabase.delete("type_income", "type_incomeName"+"=?",new String[]{type_incomeName});
        closeDatabase();
    }
    public void insertTypeIncome(String a,String b) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put("type_incomeName", a);
        values.put("description", b);
        mDatabase.insert("type_income", null, values);
        closeDatabase();
    }
    public void insertTypeExpense(String a,String b) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put("detail_type_expenseName", a);
        values.put("type_expenseID","TE11");
        values.put("description", b);
        mDatabase.insert("detail_type_expense", null, values);

        closeDatabase();
    }
    public void updateTypeExpense(String a,String b,String c) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put("detail_type_expenseName", a);
        values.put("type_expenseID", a);
        values.put("description", b);
        mDatabase.update("detail_type_expense",values,"detail_type_expenseID"+"=?",new String[]{c});
        closeDatabase();
    }
    public void updateTypeIncome(String a,String b,String c) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put("type_incomeName", a);
        values.put("description", b);
        mDatabase.update("type_income",values,"type_incomeID"+"=?",new String[]{c});
        closeDatabase();
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
                    String a=cursor.getString(cursor.getColumnIndexOrThrow("accountID"));
                    int temp=SelectChiAccount(a);
                    int temp2=SelectThuAccount(a);
                    list.add(new Country(cursor.getString(cursor.getColumnIndexOrThrow("accountName")),"user",Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("balance")))+temp+temp2));
                }

            }while(cursor.moveToNext());

        }
        cursor.close();
        return list;
    }
    public String Balance(String accountID)
    {
        List<Country> list = new ArrayList<Country>();
        openDatabase();
        String a="";
        String query = "Select * from account";
        Cursor cursor = mDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                if(accountID.equals(cursor.getString(cursor.getColumnIndexOrThrow("accountID"))) )
                {
                    a=cursor.getString(cursor.getColumnIndexOrThrow("balance"));
                }

            }while(cursor.moveToNext());

        }
        cursor.close();
        return a;
    }
    public List<String> SelectAccount(String userID)
    {
        List<String> list = new ArrayList<>();
        openDatabase();

        String query = "Select * from account";
        Cursor cursor = mDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                if(userID.equals(cursor.getString(cursor.getColumnIndexOrThrow("userID"))) )
                {
                   list.add(cursor.getString(cursor.getColumnIndexOrThrow("accountName")));
                }

            }while(cursor.moveToNext());

        }
        cursor.close();
        return list;
    }
    public List<String> SelectTypeExpense()
    {
        List<String> list = new ArrayList<>();
        openDatabase();

        String query = "Select * from detail_type_expense";
        Cursor cursor = mDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{

                    list.add(cursor.getString(cursor.getColumnIndexOrThrow("detail_type_expenseName")));


            }while(cursor.moveToNext());

        }
        cursor.close();
        return list;
    }
    public List<String> SelectTypeIncome()
    {
        List<String> list = new ArrayList<>();
        openDatabase();

        String query = "Select * from type_income";
        Cursor cursor = mDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{

                list.add(cursor.getString(cursor.getColumnIndexOrThrow("type_incomeName")));

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
    public void insertExpense(String accountID,String Giatri,String Mucchi,String Ngaychi,String Ghichu,String Denai,String userID) {
        openDatabase();
        ContentValues params = new ContentValues();
        params.put("ExpenseID",NextID(GetLastIDExpense(),"EP"));
        params.put("accountID",accountID);
        params.put("Giatri",Giatri);
        params.put("Mucchi",Mucchi );
        params.put("Ngaychi", Ngaychi);
        params.put("Ghichu", Ghichu);
        params.put("Denai",Denai);
        params.put("userID",userID);
        mDatabase.insert("Chi", null, params);
        closeDatabase();
    }
    public void UpdateExpense(String ExpenseID,String accountID,String Giatri,String Mucchi,String Ngaychi,String Ghichu,String Denai,String userID) {
        openDatabase();
        ContentValues params = new ContentValues();
        params.put("accountID",accountID);
        params.put("Giatri",Giatri);
        params.put("Mucchi",Mucchi );
        params.put("Ngaychi", Ngaychi);
        params.put("Ghichu", Ghichu);
        params.put("Denai",Denai);
        params.put("userID",userID);
        mDatabase.update("Chi",params,"ExpenseID"+"=?",new String[]{ExpenseID});
        closeDatabase();
    }
    public void UpdateIncome(String ExpenseID,String accountID,String Giatri,String Mucchi,String Ngaychi,String Ghichu,String Denai,String userID) {
        openDatabase();
        ContentValues params = new ContentValues();
        params.put("accountID",accountID);
        params.put("Giatri",Giatri);
        params.put("Mucchi",Mucchi );
        params.put("Ngaychi", Ngaychi);
        params.put("Ghichu", Ghichu);
        params.put("Denai",Denai);
        params.put("userID",userID);
        mDatabase.update("Thu",params,"IncomeID"+"=?",new String[]{ExpenseID});
        closeDatabase();
    }
    public void insertIncome(String accountID,String Giatri,String Mucchi,String Ngaychi,String Ghichu,String Denai,String userID) {
        openDatabase();
        ContentValues params = new ContentValues();
        params.put("IncomeID",NextID(GetLastIDIncome(),"IC"));
        params.put("accountID",accountID);
        params.put("Giatri",Giatri);
        params.put("Mucchi",Mucchi );
        params.put("Ngaychi", Ngaychi);
        params.put("Ghichu", Ghichu);
        params.put("Denai",Denai);
        params.put("userID",userID);
        mDatabase.insert("Thu", null, params);
        closeDatabase();
    }
    public Selectintcome selectExpense(String IncomeID){
        openDatabase();
        Selectintcome a=new Selectintcome();
        String query = "Select * from Chi";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                if(IncomeID.equals(cursor.getString(cursor.getColumnIndexOrThrow("ExpenseID")))) {

                    a.setGiattri( cursor.getString(cursor.getColumnIndexOrThrow("Giatri")));
                    a.setNgaychi(  subString(cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi")),2)+"/"+subString(cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi")),1)+"/"+subString(cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi")),0));
                    a.setGhichu( cursor.getString(cursor.getColumnIndexOrThrow("Ghichu")));
                    a.setDenai(  cursor.getString(cursor.getColumnIndexOrThrow("Denai")));

                }


            }while(cursor.moveToNext());

        }
        cursor.close();
        return a;

    }

    public Selectintcome selectIncome(String IncomeID){
        openDatabase();
        Selectintcome a=new Selectintcome();
        String query = "Select * from Thu";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                if(IncomeID.equals(cursor.getString(cursor.getColumnIndexOrThrow("IncomeID")))) {

                    a.setGiattri( cursor.getString(cursor.getColumnIndexOrThrow("Giatri")));
                    a.setNgaychi(  subString(cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi")),2)+"/"+subString(cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi")),1)+"/"+subString(cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi")),0));
                    a.setGhichu( cursor.getString(cursor.getColumnIndexOrThrow("Ghichu")));
                    a.setDenai(  cursor.getString(cursor.getColumnIndexOrThrow("Denai")));

                }


            }while(cursor.moveToNext());

        }
        cursor.close();
        return a;

    }
    private void InsertExpense(final Context context,final String ExpenseID, final String accountID, final String Giatri, final String Mucchi, final String Ngaychi, final String Ghichu, final String Denai, final String userID){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/insertExpense.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){


                }else{

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
                params.put("accountID",accountID);
                params.put("Giatri",Giatri);
                params.put("Mucchi",Mucchi);
                params.put("Ngaychi", Ngaychi);
                params.put("Ghichu", Ghichu);
                params.put("Denai",Denai);
                params.put("userID",userID);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    private void InsertIncome(final Context context,final String IncomeID, final String accountID, final String Giatri, final String Mucchi, final String Ngaychi, final String Ghichu, final String Denai, final String userID){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/insertIncome.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){
                }else{
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
                params.put("IncomeID",IncomeID);
                params.put("accountID",accountID);
                params.put("Giatri",Giatri);
                params.put("Mucchi",Mucchi);
                params.put("Ngaychi", Ngaychi);
                params.put("Ghichu", Ghichu);
                params.put("Denai",Denai);
                params.put("userID",userID);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    public void insertAccount(Context context,String accountID,String userID,String accountName,String balance) {
        openDatabase();
        String query = "Select * from account";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                if(!accountID.equals(cursor.getString(cursor.getColumnIndexOrThrow("accountID"))) )
                {
                    ContentValues values = new ContentValues();
                    values.put("accountID", accountID);
                    values.put("userID", userID);
                    values.put("accountName", accountName);
                    values.put("balance",balance);
                    mDatabase.insert("account", null, values);
                    Toast.makeText(context,""+"Đăng kí tài khoản thành công",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(context,""+"Đăng kí tài khoản thất bại",Toast.LENGTH_SHORT).show();
                }

            }while(cursor.moveToNext());

        }

    }
    private void updateAccountSever(final Context context,final String accountID, final String accountName, final String balance){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/updateAccount.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){


                }else{

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
                params.put("accountID",accountID);
                params.put("accountName",accountName);
                params.put("balance", balance);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    public void updateAccount11(Context context,String accountID, String accountName,String balance) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put("accountName", accountName);
        values.put("balance",balance);
        mDatabase.update("account",values,"accountID"+"=?",new String[]{accountID});
        closeDatabase();
    }

    public void deleteAccount(String accountID) {
        openDatabase();
        mDatabase.delete("Thu", "accountID"+"=?",new String[]{accountID});
        mDatabase.delete("Chi", "accountID"+"=?",new String[]{accountID});
        mDatabase.delete("account", "accountID"+"=?",new String[]{accountID});
        closeDatabase();
    }

    public void deleteIncome(String IncomeID ) {
        openDatabase();
        mDatabase.delete("Thu", "IncomeID"+"=?",new String[]{IncomeID});
        closeDatabase();
    }
    public void deleteExpense(String ExpenseID) {
        openDatabase();
        mDatabase.delete("Chi", "ExpenseID"+"=?",new String[]{ExpenseID});

        closeDatabase();
    }
    public void addTypeExpense(final Context context,final String userName,final String Email,final String password){
        final RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if((response.trim()).equals("success")){

                }else{

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
                params.put("userID",userName);
                params.put("userName",userName);
                params.put("password", password);
                params.put("Email", Email);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void addAccount(final Context context,final String accountID,final String userID, final String accountName,final  String balance){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://quanpn.000webhostapp.com/manage/insertAccount.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if((response.trim()).equals("success")){


                }else{

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
                params.put("accountID",accountID);
                params.put("userID",userID);
                params.put("accountName", accountName);
                params.put("balance", balance);
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
    public void InsertAccountMenu(final Context context){
        openDatabase();
        String query = "Select * from account";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                final String accountID = cursor.getString(cursor.getColumnIndexOrThrow("accountID"));
                final String userID = cursor.getString(cursor.getColumnIndexOrThrow("userID"));
                final String accountName = cursor.getString(cursor.getColumnIndexOrThrow("accountName"));
                final String balance = cursor.getString(cursor.getColumnIndexOrThrow("balance"));
                final RequestQueue requestQueue = Volley.newRequestQueue(context);
                final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://quanpn.000webhostapp.com/manage/selectAccount1.php", null,
                        new Response.Listener<JSONArray>(){
                            @Override
                            public void onResponse(JSONArray response) {
                                for(int i = 0; i<response.length(); i++){
                                    try {
                                        JSONObject obj = response.getJSONObject(i);
                                        {
                                            if(!accountID.equals(obj.getString("accountID")))
                                            {
                                                addAccount(context,accountID,userID,accountName,balance);
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
                                //Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                requestQueue.add(jsonArrayRequest);

            }while(cursor.moveToNext());

        }
        cursor.close();

    }
    public String GetLastIDExpense()
    {
        openDatabase();
        String a = "";
        String query = "Select ExpenseID from Chi";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToLast())
        {
            a= cursor.getString(cursor.getColumnIndexOrThrow("ExpenseID"));
        }
        return a;
    }
    public String GetLastIDIncome()
    {
        openDatabase();
        String a = "";
        String query = "Select IncomeID from Thu";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToLast())
        {
            a= cursor.getString(cursor.getColumnIndexOrThrow("IncomeID"));
        }

        return a;
    }
    public String NextID(String lastID, String prefixID)
    {
        if(lastID == "")
        {
            return prefixID+"0001";  // fixwidth default
        }
        int nextID = Integer.parseInt(Remove(lastID)) + 1;
        int lengthNumerID = lastID.length() - prefixID.length();
        String zeroNumber = "";
        for (int i = 1; i <= lengthNumerID; i++)
        {
            if (nextID < Math.pow(10, i))
            {
                for (int j = 1; j <= lengthNumerID - i; i++)
                {
                    zeroNumber += "0";
                }
                return prefixID + zeroNumber + nextID;
            }
        }
        return prefixID + nextID;
    }
    public int SelectThuAccount(String accountID)
    {
        openDatabase();
        int a=0;
        String query = "Select * from Thu";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do {
                String temp = cursor.getString(cursor.getColumnIndexOrThrow("accountID"));
                if (temp.equals(accountID)) {

                    a = a + Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("Giatri")));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return a;
    }
    public int SelectChiAccount(String accountID)
    {
        openDatabase();
        int a=0;
        String query = "Select * from Chi";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do {
                String temp = cursor.getString(cursor.getColumnIndexOrThrow("accountID"));
                if (temp.equals(accountID)) {

                    a = a + Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("Giatri")));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return a;
    }
    public List<Income> SelectIncomeAccountID(String accountID)
    {
        openDatabase();
        List<Income> list = new ArrayList<Income>();
        float a=0;
        String query = "Select * from Thu";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do {
                String temp = cursor.getString(cursor.getColumnIndexOrThrow("accountID"));
                if (temp.equals(accountID)) {
                    list.add(new Income("Thu: " + cursor.getString(cursor.getColumnIndexOrThrow("Mucchi")), "thutien", cursor.getString(cursor.getColumnIndexOrThrow("Giatri")),"Ngày: " +subString(cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi")),2)+"/"+subString(cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi")),1)+"/"+subString(cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi")),0), cursor.getString(cursor.getColumnIndexOrThrow("IncomeID"))));

                }
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public List<Income> SelectExpenseAccountID(String accountID)
    {
        openDatabase();
        List<Income> list = new ArrayList<Income>();
        float a=0;
        String query = "Select * from Chi";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do {
                String temp = cursor.getString(cursor.getColumnIndexOrThrow("accountID"));
                if (temp.equals(accountID)) {

                    list.add(new Income("Chi: " + cursor.getString(cursor.getColumnIndexOrThrow("Mucchi")), "anuong", cursor.getString(cursor.getColumnIndexOrThrow("Giatri")), "Ngày: "+"Ngày: " +subString(cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi")),2)+"/"+subString(cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi")),1)+"/"+subString(cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi")),0), cursor.getString(cursor.getColumnIndexOrThrow("ExpenseID"))));

                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public List<Income> SelectIncomeDatetime(String accountID,String Ngaychi)
    {
        openDatabase();
        List<Income> list = new ArrayList<Income>();
        float a=0;
        String query = "Select * from Thu";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do{
            String temp = cursor.getString(cursor.getColumnIndexOrThrow("userID"));
            String temp1 = cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi"));
            if (temp.equals(accountID)&&temp1.equals(Ngaychi)) {
                    list.add(new Income("Thu: " + cursor.getString(cursor.getColumnIndexOrThrow("Mucchi")), "thutien", cursor.getString(cursor.getColumnIndexOrThrow("Giatri")),"Tài khoản: "+Catchuoi( cursor.getString(cursor.getColumnIndexOrThrow("accountID"))), cursor.getString(cursor.getColumnIndexOrThrow("IncomeID"))));
                }
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public List<Income> SelectExpenseDatetime(String accountID,String Ngaychi)
    {
        openDatabase();
        List<Income> list = new ArrayList<Income>();
        float a=0;
        String query = "Select * from Chi";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do {
                String temp = cursor.getString(cursor.getColumnIndexOrThrow("userID"));
                String temp1 = cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi"));
                if (temp.equals(accountID)&&temp1.equals(Ngaychi)) {
                    list.add(new Income("Chi: " + cursor.getString(cursor.getColumnIndexOrThrow("Mucchi")), "thutien", cursor.getString(cursor.getColumnIndexOrThrow("Giatri")), "Tài khoản"+Catchuoi(cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi"))), cursor.getString(cursor.getColumnIndexOrThrow("ExpenseID"))));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public void UpdateAccountMenu(final Context context){
        openDatabase();
        String query = "Select * from account";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                final String accountID = cursor.getString(cursor.getColumnIndexOrThrow("accountID"));
                final String accountName = cursor.getString(cursor.getColumnIndexOrThrow("accountName"));
                final String balance = cursor.getString(cursor.getColumnIndexOrThrow("balance"));
                final RequestQueue requestQueue = Volley.newRequestQueue(context);
                final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://quanpn.000webhostapp.com/manage/selectAccount1.php", null,
                        new Response.Listener<JSONArray>(){
                            @Override
                            public void onResponse(JSONArray response) {
                                for(int i = 0; i<response.length(); i++){
                                    try {
                                        JSONObject obj = response.getJSONObject(i);
                                        {
                                            if(accountID.equals(obj.getString("accountID")))
                                            {
                                                updateAccountSever(context,accountID,accountName,balance);
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
                                //Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                requestQueue.add(jsonArrayRequest);

            }while(cursor.moveToNext());

        }
        cursor.close();

    }

    public void InsertIncomeMenu(final Context context){
        openDatabase();
        String query = "Select * from Thu";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                final String IncomeID=cursor.getString(cursor.getColumnIndexOrThrow("IncomeID"));
                final String accountID = cursor.getString(cursor.getColumnIndexOrThrow("accountID"));
                final String Giatri=cursor.getString(cursor.getColumnIndexOrThrow("Giatri"));
                final String Mucchi = cursor.getString(cursor.getColumnIndexOrThrow("Mucchi"));
                final String Ngaychi = cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi"));
                final String Ghichu = cursor.getString(cursor.getColumnIndexOrThrow("Ghichu"));
                final String Denai = cursor.getString(cursor.getColumnIndexOrThrow("Denai"));
                final String userID = cursor.getString(cursor.getColumnIndexOrThrow("userID"));
                final RequestQueue requestQueue = Volley.newRequestQueue(context);
                final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://quanpn.000webhostapp.com/manage/selectLayIncome.php", null,
                        new Response.Listener<JSONArray>(){
                            @Override
                            public void onResponse(JSONArray response) {
                                for(int i = 0; i<response.length(); i++){
                                    try {
                                        JSONObject obj = response.getJSONObject(i);
                                        {
                                            if(!(IncomeID+"("+userID+")").equals(obj.getString("IncomeID")))
                                            {
                                                InsertIncome(context,IncomeID+"("+userID+")",accountID,Giatri,Mucchi,Ngaychi,Ghichu,Denai,userID);
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
                                //Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                requestQueue.add(jsonArrayRequest);

            }while(cursor.moveToNext());

        }
        cursor.close();

    }
    public void InsertExpenseMenu(final Context context){
        openDatabase();
        String query = "Select * from Chi";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                final String IncomeID=cursor.getString(cursor.getColumnIndexOrThrow("ExpenseID"));
                final String accountID = cursor.getString(cursor.getColumnIndexOrThrow("accountID"));
                final String Giatri=cursor.getString(cursor.getColumnIndexOrThrow("Giatri"));
                final String Mucchi = cursor.getString(cursor.getColumnIndexOrThrow("Mucchi"));
                final String Ngaychi = cursor.getString(cursor.getColumnIndexOrThrow("Ngaychi"));
                final String Ghichu = cursor.getString(cursor.getColumnIndexOrThrow("Ghichu"));
                final String Denai = cursor.getString(cursor.getColumnIndexOrThrow("Denai"));
                final String userID = cursor.getString(cursor.getColumnIndexOrThrow("userID"));
                final RequestQueue requestQueue = Volley.newRequestQueue(context);
                final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://quanpn.000webhostapp.com/manage/selectLayExpense.php", null,
                        new Response.Listener<JSONArray>(){
                            @Override
                            public void onResponse(JSONArray response) {
                                for(int i = 0; i<response.length(); i++){
                                    try {
                                        JSONObject obj = response.getJSONObject(i);
                                        {
                                            if(!(IncomeID+"("+userID+")").equals(obj.getString("ExpenseID")))
                                            {
                                                InsertExpense(context,IncomeID+"("+userID+")",accountID,Giatri,Mucchi,Ngaychi,Ghichu,Denai,userID);
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
                                //Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                requestQueue.add(jsonArrayRequest);

            }while(cursor.moveToNext());

        }
        cursor.close();

    }
    public String Remove(String String)
            {
                String=String.replace("IC","0");
                String=String.replace("EP","0");
                return String;
    }
    public String Catchuoi(String String)
    {
        String a=null;
        String[] strArr;
        strArr =String.split("-");
        for(int i = 0; i < strArr.length; i++){
            a=(strArr[i]);
        }
    return  a;
    }
    public String subString(String String,int k )
    {
        String a=null;
        String[] strArr;
        strArr =String.split("-");
        for(int i = 0; i < strArr.length; i++){
            a=(strArr[k]);
        }
        return  a;
    }
}

package com.learnadroid.myfirstapp.Connect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class DbBackend extends DbObject {

    public DbBackend(Context context) {
        super(context);
    }
    public boolean Dangnhap(String Tentaikhoan,String Matkhau){
        String query = "Select * from user";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);

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
        String query = "Select * from user";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);

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

    public void insertUser(String userID,String userName,String Email,String password) {

        ContentValues values = new ContentValues();
        values.put("userID", userID);
        values.put("userName", userName);
        values.put("password", password);
        values.put("Email", Email);
        this.getDbConnection().insert("user", null, values);
        this.closeDbConnection();
    }



    public String[] dictionaryWords(){
        String query = "Select * from dictionary where _id<500";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);
        ArrayList<String> wordTerms = new ArrayList<String>();
            if(cursor.moveToFirst()){
                do{
                    String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));
                    wordTerms.add(word);
                }while(cursor.moveToNext());


        }
        cursor.close();
        String[] dictionaryWords = new String[wordTerms.size()];
        dictionaryWords = wordTerms.toArray(dictionaryWords);
        return dictionaryWords;
    }
    public String[] LoadWords(String Key){
        String query = "Select * from dictionary where word LIKE '" + Key+"%'";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);
        ArrayList<String> wordTerms = new ArrayList<String>();

            if (cursor.moveToFirst()) {
                do {
                    String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));
                    wordTerms.add(word);
                    if(wordTerms.size()>500)
                        break;
                } while (cursor.moveToNext());



        }
        cursor.close();
        String[] dictionaryWords = new String[wordTerms.size()];
        dictionaryWords = wordTerms.toArray(dictionaryWords);
        return dictionaryWords;
    }




}

package com.example.gp;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.provider.SearchRecentSuggestions;
public class UserDB  extends SQLiteOpenHelper{
    private  static  String DataBaseName="UserDatabase";
    SQLiteDatabase userDatabase;

    public String USerName;
    public String USerage;
    public String USermail;
    public String USerPassword;


    public UserDB(Context context){
        super(context,DataBaseName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table user" +
                "(userID integer primary key autoincrement," + "Name text not null, age text not null," +
                "Email text not null, password text not null)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists user");
        onCreate(sqLiteDatabase);

    }
    public  void AddnewUser(String GmailAccount, String Password,String age, String name){

        ContentValues row = new ContentValues();
        row.put("Name", name);
        row.put("age", age);
        row.put("Email", GmailAccount);
        row.put("password", Password);

        userDatabase = getWritableDatabase();
        userDatabase.insert("user", null, row);
        userDatabase.close();

    }
    //______________________________________
    public Cursor fetchAllusers(){
        userDatabase= getReadableDatabase();
        String [] rowDetails ={"Name","age","Email","password"};
        Cursor cursor = userDatabase.query("user",rowDetails,null,null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        userDatabase.close();
        return  cursor;
    }
    public void deleteAll()
    {
        userDatabase=getWritableDatabase();
        userDatabase.delete("user",null,null);
        userDatabase.close();
    }
    public Boolean CheckEmail( String mail){
        userDatabase=getReadableDatabase();
        Cursor C= userDatabase.rawQuery("select * from user where Email=?",new String[]{mail});

        if (C.getCount()>0)
            return  false;
        else
            return true;
    }

    public  Boolean Login (String mail, String Password){
        userDatabase=getReadableDatabase();
        Cursor C= userDatabase.rawQuery("select * from user where Email=? and password=?",new String[]{mail ,Password});

        if (C.getCount()>0)
            return  true;
        else
            return false;
    }
    public Cursor GetUserData(String GMail){
        userDatabase= getReadableDatabase();
        String [] rowDetails ={"Name","age","password","Email="+GMail};
        Cursor cursor= userDatabase.rawQuery("select * from user where Email like?",new String[]{GMail});
        cursor.moveToFirst();

        if(cursor!=null) {
            userDatabase.close();
            return cursor;
        }
        userDatabase.close();
        return null;

    }
    public  void UpdateUSerInfo(String Mail,String Name,String Pass,String Age){

        userDatabase =getWritableDatabase();
        ContentValues row=new ContentValues();
        row.put("Name", Name);
        row.put("Email", Mail);
        row.put("age", Age);
        row.put("password", Pass);
        userDatabase.update("user",row,"Email like?",new String[]{Mail});
        userDatabase.close();
    }

}

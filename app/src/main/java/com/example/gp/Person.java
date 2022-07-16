package com.example.gp;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

public class Person {
    public String USerName;
    public String USerage;
    public String USermail;
    Context mContext;


    public Context getmContext() {
        return mContext;
    }

    public String USerPassword;
    public UserDB DBPerson;

    public String getUSerName() {
        return USerName;
    }

    public void setUSerName(String USerName) {
        this.USerName = USerName;
    }

    public String getUSerage() {
        return USerage;
    }

    public void setUSerage(String USerage) {
        this.USerage = USerage;
    }

    public String getUSermail() {
        return USermail;
    }

    public void setUSermail(String USermail) {
        this.USermail = USermail;
    }

    public String getUSerPassword() {
        return USerPassword;
    }

    public void setUSerPassword(String USerPassword) {
        this.USerPassword = USerPassword;
    }

    public Person(Context c, String Name, String Age, String Mail, String Password) {
        USerName = Name;
        USermail = Mail;
        USerage = Age;
        USerPassword = Password;
        mContext = c;

    }

    public Person(Context c) {

        mContext = c;

    }

    public boolean Login(String Gmail, String Pass) {
        DBPerson = new UserDB(mContext);
        if (DBPerson.Login(Gmail, Pass) == true) {
            USermail = Gmail;
            USerPassword = Pass;
            Cursor C = DBPerson.GetUserData(Gmail);
            USerName = C.getString(1);
            USerage = C.getString(2);
        }
        return DBPerson.Login(Gmail, Pass);

    }

    public boolean  CheckIfAdmin() {
        DBPerson = new UserDB(mContext);

        if (USermail.equals("shimaahamdy112001@gmail.com") || USermail.equals("hagarhossam610@gmail.com") ) {
            Cursor C=DBPerson.GetUserData(USermail);
            return true;
        }
return false ;
    }
    public String  GetAdminID(String G) {
        DBPerson = new UserDB(mContext);

        if (USermail.equals("shimaahamdy112001@gmail.com") ||USermail.equals("hagarhossam610@gmail.com")) {
            Cursor C=DBPerson.GetUserData(G);
            return C.getString(0);
        }
        return null ;
    }
}

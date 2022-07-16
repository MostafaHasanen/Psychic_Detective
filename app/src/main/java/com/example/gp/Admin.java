package com.example.gp;

import android.content.Context;

import org.checkerframework.checker.units.qual.C;

public class Admin extends Person{
    public String getAdminCode() {
        return AdminCode;
    }
Context Ct;
    public void setAdminCode(String adminCode) {
        AdminCode = adminCode;
    }

    public String AdminCode;
    public Admin(Context C, String Code, String Name, String Age, String Mail , String Password) {
        super( C,Name, Age,  Mail , Password);
        Ct=C;
        AdminCode=Code;
    }

}

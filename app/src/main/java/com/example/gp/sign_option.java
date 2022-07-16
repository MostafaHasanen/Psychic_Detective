package com.example.gp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class sign_option extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_option);


        Button BtnsinIN = (Button) findViewById(R.id.SIGNIN);
        BtnsinIN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent Main = new Intent(sign_option.this, com.example.gp.Login.class);
                startActivity(Main);
            }
        });



        Button Btnsup = (Button) findViewById(R.id.SIGNUP);
        Btnsup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent Main2 = new Intent(sign_option.this, com.example.gp.Register.class);
                startActivity(Main2);
            }
        });


    }
}
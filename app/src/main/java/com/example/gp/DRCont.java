package com.example.gp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DRCont extends AppCompatActivity {

    ListView Doctors;
    String Names[] = {"Ahmed", "Mariam","Mounir","Safaa"};
    String[] Degree={"BS","BS","BS","BS"};
    String[] PhoneNo={"01148522079","01286078096","01579600875","01096752746"};
    String[] Email= {"Ahmed@gmail.com", "Mariam@gmail.com","Mounir@gmail.com","Safaa@gmail.com"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drcont);

        Doctors = (ListView) findViewById(R.id.ListviewDR);
        BottomNavigationView bottomNavigationView=findViewById(R.id.men);
        bottomNavigationView.setSelectedItemId(R.id.home);
        String GmailPageUSer = getIntent().getExtras().getString("Email");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.home:
                        Intent Home = new Intent(DRCont.this, LevelsMenu.class);
                        Home.putExtra("Email", GmailPageUSer);
                        startActivity(Home);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Dr:
                        Toast.makeText(getApplicationContext(),"You are already in Account Info Page", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.profile:
                        Intent Account = new Intent(DRCont.this, Account.class);

                        Account.putExtra("Email", GmailPageUSer);
                        startActivity(Account);
                        overridePendingTransition(0, 0);

                        return true;

                    case R.id.Logout:
                        Intent Logout = new Intent(DRCont.this, sign_option.class);
                        Logout.putExtra("Email", GmailPageUSer);
                        startActivity(Logout);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;

            }

        });



      DRBaseAdapter DrBaseAdapter = new DRBaseAdapter(getApplicationContext(), Names, Degree, Email, PhoneNo);
        Doctors.setAdapter(DrBaseAdapter);
    }}

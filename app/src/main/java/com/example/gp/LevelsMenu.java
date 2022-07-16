package com.example.gp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LevelsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelsmenu);
        ImageView Que= (ImageView) findViewById(R.id.imageskin);
        ImageView Voice=(ImageView)findViewById(R.id.imagehair);
        ImageView Img=(ImageView)findViewById(R.id.imagebody);
        BottomNavigationView bottomNavigationView=findViewById(R.id.men);
        bottomNavigationView.setSelectedItemId(R.id.home);
        String GmailPageUSer= getIntent().getExtras().getString("Email");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//String Mail_Text;
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent Home = new Intent(LevelsMenu.this, LevelsMenu.class);
                        Home.putExtra("Email", GmailPageUSer);
                        startActivity(Home);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Dr:
                        Intent Dr = new Intent(LevelsMenu.this, DRCont.class);
                        Dr.putExtra("Email", GmailPageUSer);
                        startActivity(Dr);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        Intent Account = new Intent(LevelsMenu.this, Account.class);

                        Account.putExtra("Email", GmailPageUSer);
                        startActivity(Account);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.Logout:
                        Intent Logout = new Intent(LevelsMenu.this, sign_option.class);
                        Logout.putExtra("Email", GmailPageUSer);
                        startActivity(Logout);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;

            }

        });

        Que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent que =  new Intent(LevelsMenu.this,question_activity.class);
                que.putExtra("Email", GmailPageUSer);
                startActivity(que);

            }
        });
        Voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent que =  new Intent(LevelsMenu.this,Voice.class);
                que.putExtra("Email", GmailPageUSer);
                startActivity(que);

            }
        });
        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent que =  new Intent(LevelsMenu.this,Image.class);
                que.putExtra("Email", GmailPageUSer);
                startActivity(que);

            }
        });

    }
    }

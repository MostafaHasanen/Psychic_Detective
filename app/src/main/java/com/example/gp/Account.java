package com.example.gp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        UserDB CurrentUserAccount = new UserDB(this);
        String GmailPageUSer = getIntent().getExtras().getString("Email");
        Cursor CurrentUserrCursor = CurrentUserAccount.GetUserData(GmailPageUSer);
        ((TextView) findViewById(R.id.GmailAccountDispl)).setText(GmailPageUSer);
        if (CurrentUserrCursor == null) {
            Toast.makeText(getApplicationContext(), "NOT FOUND", Toast.LENGTH_LONG).show();
        }
        else
        {
            ((EditText) findViewById(R.id.NameUDispl)).setText(CurrentUserrCursor.getString(1).toString());
            ((EditText) findViewById(R.id.AgeUDispl)).setText(CurrentUserrCursor.getString(2).toString());

            ((EditText) findViewById(R.id.PasswordUDispl)).setText(CurrentUserrCursor.getString(4).toString());


        }
        BottomNavigationView bottomNavigationView=findViewById(R.id.men);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                                     @Override
                                     public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//String Mail_Text;
                                         switch (item.getItemId()) {
                                             case R.id.home:
                                                 Intent Home = new Intent(Account.this, LevelsMenu.class);
                                                 Home.putExtra("Email", GmailPageUSer);
                                                 startActivity(Home);
                                                 overridePendingTransition(0, 0);
                                                 return true;
                                             case R.id.Dr:
                                                 Intent Dr = new Intent(Account.this, DRCont.class);
                                                 Dr.putExtra("Email", GmailPageUSer);
                                                 startActivity(Dr);
                                                 overridePendingTransition(0, 0);
                                                 return true;
                                             case R.id.profile:
                                                 Intent Account = new Intent(Account.this, Account.class);
                                                 //Mail_Text = getIntent().getExtras().getString("Email");
                                                 Account.putExtra("Email", GmailPageUSer);
                                                 startActivity(Account);
                                                 overridePendingTransition(0, 0);
                                                 return true;

                                             case R.id.Logout:
                                                 Intent Logout = new Intent(Account.this, sign_option.class);
                                                 Logout.putExtra("Email", GmailPageUSer);
                                                 startActivity(Logout);
                                                 overridePendingTransition(0, 0);
                                                 return true;
                                         }
                                         return false;

                                     }

        });

Button Upd= (Button)findViewById(R.id.Update);
        Upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentUserAccount.UpdateUSerInfo(GmailPageUSer,
                        ((EditText) findViewById(R.id.NameUDispl)).getText().toString()
                        ,((EditText) findViewById(R.id.PasswordUDispl)).getText().toString()
                        , ((EditText) findViewById(R.id.AgeUDispl)).getText().toString());
                Toast.makeText(getApplicationContext(), "Updated successfully", Toast.LENGTH_LONG).show();


            }
        });
}
}
package com.example.gp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.checkerframework.checker.units.qual.A;

public class Login extends AppCompatActivity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        final EditText Mail_Text= (EditText)findViewById(R.id.emailLogtext);
        final EditText Password_Text= (EditText)findViewById(R.id.LoginPassword);
        final Button LoginBut= (Button)findViewById(R.id.Login2button);



        LoginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Mail_Text.getText().toString().equals ("") || Password_Text .getText().toString().equals (""))
                {
                    Toast.makeText(getApplicationContext(), "Please Fill Data", Toast.LENGTH_SHORT).show();

                }
                else {
                     Person PersonU=new Person(Login.this);
                    Boolean checkLogin = PersonU.Login(Mail_Text.getText().toString(),Password_Text.getText().toString());
                    if (checkLogin== true){

                        if ( PersonU.CheckIfAdmin()==true){
                            Toast.makeText(getApplicationContext(),"Welcome Admin:"+PersonU.USerName,Toast.LENGTH_LONG).show();
                         Admin   Pers=new Admin(Login.this, PersonU.GetAdminID(PersonU.getUSermail()),PersonU.getUSerName(),PersonU.getUSerage(),PersonU.getUSermail(),PersonU.getUSerName());
                            Intent Home = new Intent(Login.this, quote_start.class);
                            Home.putExtra("Email", Mail_Text.getText().toString());
                            startActivity(Home);

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Welcome " + PersonU.getUSerName(), Toast.LENGTH_LONG).show();
                            User   Pers=new User(Login.this,PersonU.getUSerName(),PersonU.getUSerage(),PersonU.getUSermail(),PersonU.getUSerName());
                            Intent Home = new Intent(Login.this, quote_start.class);
                            Home.putExtra("Email", Mail_Text.getText().toString());
                            startActivity(Home);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Wrong Password or E-mail",Toast.LENGTH_LONG).show();
                        Mail_Text.setText("");
                        Password_Text.setText("");

                    }

                }
            }
        });
            }
        }

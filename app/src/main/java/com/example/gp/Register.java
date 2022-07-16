package com.example.gp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    Cursor CurrentUserrCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button RegistButton= (Button)findViewById(R.id.Rbutton);
      //  Button ClearButton= (Button)findViewById(R.id.ClearButton);
        final EditText namet= (EditText)findViewById(R.id.NameText);
        final EditText aget= (EditText)findViewById(R.id.ageText);
        final EditText mailt= (EditText)findViewById(R.id.mailText);
        final EditText passwordt= (EditText)findViewById(R.id.PasswordText);
        final EditText Cpasswordt= (EditText)findViewById(R.id.confirmPasswordtext);

       final  UserDB newuser= new UserDB(this);

        RegistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean  check= newuser.CheckEmail(mailt.getText().toString());
                if (namet.getText().toString().equals("") ||
                    mailt.getText().toString().equals("") ||
                        passwordt.getText().toString().equals("")
                        || Cpasswordt.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field empty", Toast.LENGTH_LONG).show();

                }

                else if (check== false)
                {
                    Toast.makeText(getApplicationContext(), "E-mail is already exists", Toast.LENGTH_LONG).show();
                }
                else if(!mailt.getText().toString().contains("@gmail.com"))
                {
                    Toast.makeText(getApplicationContext(), "Wrong Format Need @gmail.com", Toast.LENGTH_LONG).show();
                }

                else if(passwordt.getText().toString().length()<=6)
                {
                    Toast.makeText(getApplicationContext(), "Weak Password ,password must be greater than 6", Toast.LENGTH_LONG).show();
                }

                else if (!(passwordt.getText().toString().equals(Cpasswordt.getText().toString()))) {

                    Toast.makeText(getApplicationContext(), "Confirm Password not matching", Toast.LENGTH_LONG).show();
                }
                else
                {
                    newuser.AddnewUser(mailt.getText().toString(), passwordt.getText().toString(),
                            aget.getText().toString()
                            , namet.getText().toString());

                    Toast.makeText(getApplicationContext(), "Successufly Regisiration", Toast.LENGTH_SHORT).show();

                    Intent Home = new Intent(Register.this,LevelsMenu.class);
                    Home.putExtra("Email",mailt.getText().toString());
                    startActivity(Home);
                }
            }
        });


    }
}
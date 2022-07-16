package com.example.gp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class quote_start extends AppCompatActivity {
   // Button Let_button;

    @Override
   protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.quote_start);

       Button Btnlet = (Button) findViewById(R.id.buttonl);
          Btnlet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent Main = new Intent(quote_start.this, LevelsMenu.class);
                String GmailPageUSer= getIntent().getExtras().getString("Email");
                Main.putExtra("Email", GmailPageUSer);
                startActivity(Main);
            }
        });

        }
}


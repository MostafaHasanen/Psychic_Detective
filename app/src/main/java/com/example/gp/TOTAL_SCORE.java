package com.example.gp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class TOTAL_SCORE extends AppCompatActivity {

    BarEntry barEntry;
    BarEntry barEntry1;
    BarEntry barEntry2;
    BarEntry barEntry3;
    BarEntry barEntry4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("dfg");
        setContentView(R.layout.activity_total_score);
        int s = getIntent().getExtras().getInt("total");
        String GmailPageUSer= getIntent().getExtras().getString("Email");
        BottomNavigationView bottomNavigationView=findViewById(R.id.men);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String Mail_Text;

                switch (item.getItemId()) {
                    case R.id.home:
                        Intent Home = new Intent(TOTAL_SCORE.this, LevelsMenu.class);
                        Home.putExtra("Email", GmailPageUSer);
                        startActivity(Home);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Dr:
                        Intent Dr = new Intent(TOTAL_SCORE.this, DRCont.class);
                        Dr.putExtra("Email", GmailPageUSer);
                        startActivity(Dr);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        Intent Account = new Intent(TOTAL_SCORE.this, Account.class);
                        Account.putExtra("Email", GmailPageUSer);
                        startActivity(Account);
                        overridePendingTransition(0, 0);

                        return true;

                    case R.id.Logout:
                        Intent Logout = new Intent(TOTAL_SCORE.this, sign_option.class);
                        Logout.putExtra("Email", GmailPageUSer);
                        startActivity(Logout);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;

            }

        });



        TextView Output = findViewById(R.id.textView2);
      BarChart chart = findViewById(R.id.Chart);
        ArrayList<BarEntry> arr = new ArrayList<>();

        int S = getIntent().getExtras().getInt("total");

        int g=S;

        YAxis y = chart.getAxisLeft();
        y.setAxisMaxValue(100);
        y.setAxisMinValue(0);
        XAxis x= chart.getXAxis();
        x.setAxisMaximum(10);
        x.setAxisMinimum(1);
        chart.getXAxis().setDrawLabels(false);
        BarDataSet barDataSet=new BarDataSet(arr," ");
        chart.setData(new BarData(barDataSet));
        //   chart.animateXY(50,50);
        chart.getDescription().setEnabled(true);
        chart.getDescription().setText("Your score in Blue Column");
        chart.getDescription().setTextSize(10f);
        chart.getDescription().setPosition(1020,40);
        chart.getDescription().setTextColor(ColorTemplate.rgb("#3792cb"));
        BarData data= new BarData(barDataSet);
        data.setValueTextSize(0f);
        barEntry = new BarEntry(2.5f,25);
        barEntry1 = new BarEntry(4.5f, 50);
        barEntry2 = new BarEntry(7, 25);
        arr.add(barEntry);
        arr.add(barEntry1);
        arr.add(barEntry2);

        if (S <= 42) {
            Output.setText(String.valueOf("You Need to Go to Voice Test  and Image Test to get more accurate result "));
            barDataSet.setColors(ColorTemplate.rgb("#3792cb"),Color.GRAY,Color.GRAY,Color.GRAY,Color.GRAY,Color.GRAY);
        } else if (S <= 84) {
            Output.setText(String.valueOf("You need to meet new people with positive energy and love for life ," +
                    " Participation in some social activities and charities " +
                    "and Reading especially books that give life joy."));
            barDataSet.setColors(Color.GRAY,ColorTemplate.rgb("#3792cb"),Color.GRAY,Color.GRAY,Color.GRAY,Color.GRAY);
        } else {
            Output.setText(String.valueOf("You are mentally stable"));
            barDataSet.setColors(Color.GRAY,Color.GRAY,ColorTemplate.rgb("#3792cb"),Color.GRAY,Color.GRAY,Color.GRAY);

        }


    }
}
package com.example.gp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.apache.commons.math3.analysis.function.Log;


public class question_activity extends AppCompatActivity {
    //RadioButton radioButton;
    RadioGroup radio1;
    RadioGroup radio2;

    RadioButton SA1;
    RadioButton SA2;

    RadioButton SWA1;
    RadioButton SWA2;
    RadioButton LA1;
    RadioButton LA2;
    RadioButton NN1;
    RadioButton NN2;

    RadioButton LDS1;
    RadioButton LDS2;
    RadioButton SWDS1;
    RadioButton SWDS2;
    RadioButton SDS1;
    RadioButton SDS2;
    int[] NegativeQS = {1, 2, 3, 8, 9, 11, 12, 13, 17, 18}; //negative array

    int Score = 0;// total score for person
    int PageQNum = 1;
    com.example.gp.QsAnswer[] MyQs = new com.example.gp.QsAnswer[18];// array have answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        BottomNavigationView bottomNavigationView=findViewById(R.id.men);
        bottomNavigationView.setSelectedItemId(R.id.home);

        String GmailPageUSer= getIntent().getExtras().getString("Email");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
String Mail_Text;
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent Home = new Intent(question_activity.this, LevelsMenu.class);
                        Home.putExtra("Email", GmailPageUSer);
                        startActivity(Home);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Dr:
                        Intent Dr = new Intent(question_activity.this, DRCont.class);
                        Dr.putExtra("Email", GmailPageUSer);
                        startActivity(Dr);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        Intent Account = new Intent(question_activity.this, Account.class);

                        Account.putExtra("Email", GmailPageUSer);

                        startActivity(Account);
                        overridePendingTransition(0, 0);

                        return true;

                    case R.id.Logout:
                        Intent Logout = new Intent(question_activity.this, sign_option.class);
                        Logout.putExtra("Email", GmailPageUSer);
                        startActivity(Logout);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;

            }

        });


        String[] QText = {"1-I like most parts of my personality.",
                "2-When I look at the story of my life, I am pleased with how things have turned out so far.",
                " 3-Some people wander aimlessly through life, but I am not one of them.",
                " 4-The demands of everyday life often get me down.",
                "5-In many ways I feel disappointed about my achievements in life.",
                "6-Maintaining close relationships has been difficult and frustrating for me.",
                "7-I live life one day at a time and don’t really think about the future.",
                "8-In general, I feel I am in charge of the situation in which I live.",
                " 9-I am good at managing the responsibilities of daily life.",
                "10-I sometimes feel as if I’ve done all there is to do in life.",
                "11-For me, life has been a continuous process of learning, changing, and growth.",
                " 12-I think it is important to have new experiences that challenge how I think about myself and the world.",
                "13-People would describe me as a giving person, willing to share my time with others.",
                "14-I gave up trying to make big improvements or changes in my life a long time ago.",
                "15-I tend to be influenced by people with strong opinions.",
                "16-I have not experienced many warm and trusting relationships with others.",
                "17-I have confidence in my own opinions, even if they are different from the way most other people think.",
                "18-I judge myself by what I think is important, not by the values of what others think is important.",
                "LLL", "HHH"
        };
        final int[] i = {0};// qusetion number
        Button Btn = findViewById(R.id.Nextbtn);
        Button BBtn = findViewById(R.id.Backbtn);

        TextView Q1 = findViewById(R.id.Q1);
        TextView Q2 = findViewById(R.id.Q2);
        //TextView MYScore= findViewById(R.id.textView);
        radio1 = findViewById(R.id.RadioGroup1);
        radio2 = findViewById(R.id.RadioGroup2);

        SA1 = findViewById(R.id.Strong_agree1);
        SA2 = findViewById(R.id.Strong_agree2);

        SWA1 = findViewById(R.id.Somewhat_agree1);
        SWA2 = findViewById(R.id.Somewhat_agree2);

        LA1 = findViewById(R.id.ALittle_agree1);
        LA2 = findViewById(R.id.ALittle_agree2);

        NN1 = findViewById(R.id.Neither_nor1);
        NN2 = findViewById(R.id.Neither_nor2);

        LDS1 = findViewById(R.id.ALittle_disagree1);
        LDS2 = findViewById(R.id.ALittle_disagree2);

        SWDS1 = findViewById(R.id.Somewhat_disagree1);
        SWDS2 = findViewById(R.id.Somewhat_disagree2);

        SDS1 = findViewById(R.id.Strong_disagree1);
        SDS2 = findViewById(R.id.Strong_disagree2);
        Q1.setText(QText[i[0]]);// first q in first page
        Q2.setText(QText[i[0] + 1]);// 2 question

        // next button
        Btn.setOnClickListener(view -> {
                    calcScore(i[0]); // fun calcscore
                    boolean flag = error(i[0], i[0] + 1);

            // MYScore.setText(String.valueOf(Score));

                    if (!flag) { // if there is question is not answered
                        Toast.makeText(question_activity.this, "please answer all question ", Toast.LENGTH_LONG).show();
                    } else {
                        i[0] += 2;
                        if (i[0] > 17) {
                            int T_SCORE = com.example.gp.QsAnswer.Total_Score(MyQs);
                            Intent Sccore = new Intent(question_activity.this, TOTAL_SCORE.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("total", T_SCORE);
                            bundle.putString("Email", GmailPageUSer);
                            Sccore.putExtras(bundle);
                           // Sccore.putExtras(bundle);
                            startActivity(Sccore);


                        } else {

                            Q1.setText(QText[i[0]]);//set the next page
                            Q2.setText(QText[i[0] + 1]);
                            PageQNum += 1;

                            com.example.gp.QsAnswer QA11 = com.example.gp.QsAnswer.Search(i[0], MyQs);//search on question


                            if (QA11 != null && !QA11.Ans.equals("")) {
                                CheckRB(QA11.Ans, 1);// bgeb el answer el 2adema


                            } else {
                                radio1.clearCheck();// msh mtgaweb yms7 egbat el so2al eli ablo
                            }
                            com.example.gp.QsAnswer QA22 = com.example.gp.QsAnswer.Search(i[0] + 1, MyQs);

                            if (QA22 != null && !QA22.Ans.equals("")) {
                                CheckRB(QA22.Ans, 2);
                            } else {
                                radio2.clearCheck();
                            }
                        }
                    }
                }
        );
        BBtn.setOnClickListener(view -> {
            calcScore(i[0]);
            i[0] -= 2; //brg3 el so2aleen
            PageQNum -= 1;
            Q1.setText(QText[i[0]]);// brg3 el so2al ely 2bl da
            Q2.setText(QText[i[0] + 1]);

            com.example.gp.QsAnswer QA11 = com.example.gp.QsAnswer.Search(i[0], MyQs);
            com.example.gp.QsAnswer QA22 = com.example.gp.QsAnswer.Search(i[0] + 1, MyQs);
            if (QA11 == null) {
                radio1.clearCheck();
            }
            if (QA22 == null) {
                radio2.clearCheck();
            }
            if (QA11 != null && !QA11.Ans.equals("")) {
                CheckRB(QA11.Ans, 1);//brg3 el haga eli checked (radiobutton) abl kda
                Score -= QA11.AnsScore;//record whatsapp


            }
            if (QA22 != null && !QA22.Ans.equals("")) {
                CheckRB(QA22.Ans, 2);
                Score -= QA22.AnsScore;
            }
            //MYScore.setText(String.valueOf(Score));
        });
    }

    public void calcScore(int i) {

        int CScoreQ1 = 0; // score for 1st question
        int CScoreQ2 = 0;//score for 2st question
        com.example.gp.QsAnswer Q1;// object from class
        com.example.gp.QsAnswer Q2;// object from class
        String QA1 = "";// n7ot elagaba
        String QA2 = "";

        if (SA1.isChecked()) { //checking in radio button 1
            CScoreQ1 = 1;
            QA1 = "SA1";


        }
        if (SWA1.isChecked()) {
            CScoreQ1 = 2;
            QA1 = "SWA1";


        }
        if (LA1.isChecked()) {
            CScoreQ1 = 3;
            QA1 = "LA1";

        }
        if (NN1.isChecked()) {
            CScoreQ1 = 4;
            QA1 = "NN1";


        }
        if (LDS1.isChecked()) {
            CScoreQ1 = 5;
            QA1 = "LDS1";

        }
        if (SWDS1.isChecked()) {
            CScoreQ1 = 6;
            QA1 = "SWDS1";


        }
        if (SDS1.isChecked()) {
            CScoreQ1 = 7;
            QA1 = "SDS1";


        }

//_________
        if (SA2.isChecked()) {
            CScoreQ2 = 1;
            QA2 = "SA2";
        }
        if (SWA2.isChecked()) {
            CScoreQ2 = 2;
            QA2 = "SWA2";


        }
        if (LA2.isChecked()) {
            CScoreQ2 = 3;
            QA2 = "LA2";


        }
        if (NN2.isChecked()) {
            CScoreQ2 = 4;
            QA2 = "NN2";


        }
        if (LDS2.isChecked()) {
            CScoreQ2 = 5;
            QA2 = "LDS2";


        }
        if (SWDS2.isChecked()) {
            CScoreQ2 = 6;
            QA2 = "SWDS2";


        }
        if (SDS2.isChecked()) {
            CScoreQ2 = 7;
            QA2 = "SDS2";


        }

        int FlagQ1 = 0;
        int FlagQ2 = 0;
        for (int j = 0; j < NegativeQS.length; j++) { //check if  question neg
            if (NegativeQS[j] == i + 1) { //3shan el arrray 1 based and  i(ques number ) zero based
                FlagQ1 = 1;
            }
            if (NegativeQS[j] == i + 2) {
                FlagQ2 = 1;
            }
        }
        //-----------------
        if (FlagQ1 == 1 && CScoreQ1 != 0) {// calc el equation if negative
            CScoreQ1 = 8 - CScoreQ1;
            // Score += CScoreQ1;


        }
       /* else {
            Score += CScoreQ1;

        }*/
        if (FlagQ2 == 1 && CScoreQ2 != 0) {
            CScoreQ2 = 8 - CScoreQ2;
            //  Score += CScoreQ2;


        }
        /*else {
            Score += CScoreQ2;

        }*/
        if (!QA1.equals("")) {//if answer is not empty
            com.example.gp.QsAnswer Q = com.example.gp.QsAnswer.Search(i, MyQs); //btrg3 el so2al aw btrg3 null lw elso2al msh metgaweb
            if (Q == null) {
                Q1 = new com.example.gp.QsAnswer(i, PageQNum, CScoreQ1, QA1, com.example.gp.QsAnswer.Count);
                MyQs[com.example.gp.QsAnswer.Count - 1] = Q1; // when create new object count is ++ and array is zero based
            } else {
                //  Score-=MyQs[Q.indexInArr].AnsScore;
                MyQs[Q.indexInArr].setAns(QA1);
                MyQs[Q.indexInArr].setAnsScore(CScoreQ1);

            }
        }
        if (!QA2.equals("")) {
            com.example.gp.QsAnswer QQ = com.example.gp.QsAnswer.Search(i + 1, MyQs);
            if (QQ == null) {
                Q2 = new com.example.gp.QsAnswer(i + 1, PageQNum, CScoreQ2, QA2, com.example.gp.QsAnswer.Count);
                MyQs[com.example.gp.QsAnswer.Count - 1] = Q2;
            } else {
                MyQs[QQ.indexInArr].setAns(QA2);
                MyQs[QQ.indexInArr].setAnsScore(CScoreQ2);
            }
        }

    }

    //________________________________________________________-
    public void CheckRB(String RB, int QN) {// 3shan lma y3ml back ygeeb el egabat
        if (QN == 1) {
            if (RB.equals("SA1")) {
                SA1.setChecked(true);
            }
            if (RB.equals("SWA1")) {
                SWA1.setChecked(true);
            }
            if (RB.equals("LA1")) {
                LA1.setChecked(true);
            }
            if (RB.equals("NN1")) {
                NN1.setChecked(true);
            }
            if (RB.equals("LDS1")) {
                LDS1.setChecked(true);
            }
            if (RB.equals("SWDS1")) {
                SWDS1.setChecked(true);
            }
            if (RB.equals("SDS1")) {
                SDS1.setChecked(true);
            }
        } else {
            if (RB.equals("SA2")) {
                SA2.setChecked(true);

            }
            if (RB.equals("SWA2")) {
                SWA2.setChecked(true);
            }
            if (RB.equals("LA2")) {
                LA2.setChecked(true);
            }
            if (RB.equals("NN2")) {
                NN2.setChecked(true);
            }
            if (RB.equals("LDS2")) {
                LDS2.setChecked(true);
            }
            if (RB.equals("SWDS2")) {
                SWDS2.setChecked(true);
            }
            if (RB.equals("SDS2")) {
                SDS2.setChecked(true);
            }
        }
    }

    public boolean error(int i, int j) {
//bt2of fe akher page w tshof lw fe as2la msh mtgawba
        if (i == 16 && j == 17) {

            for (int y = 0; y < MyQs.length; y++) {
                if (MyQs[y] == null) {
                    return false;
                }
            }
        }
        return true;
    }


}
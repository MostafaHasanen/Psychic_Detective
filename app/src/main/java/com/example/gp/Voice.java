package com.example.gp;

import android.Manifest;
import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;

import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;


public class Voice extends AppCompatActivity {

    private static int MICROPHONE_PERMISSION_CODE = 200;
    TextView textView;

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    public static String filename = "testRecordingFile.wav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        // Button image = findViewById(R.id.imageV);
       // Button back = findViewById(R.id.HomeV);
        textView = findViewById(R.id.textView);
        BottomNavigationView bottomNavigationView=findViewById(R.id.men);
        bottomNavigationView.setSelectedItemId(R.id.home);
        String GmailPageUSer= getIntent().getExtras().getString("Email");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String Mail_Text;

                switch (item.getItemId()) {
                    case R.id.home:
                        Intent Home = new Intent(Voice.this, LevelsMenu.class);
                        Home.putExtra("Email", GmailPageUSer);
                        startActivity(Home);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Dr:
                        Intent Dr = new Intent(Voice.this, DRCont.class);
                        Dr.putExtra("Email", GmailPageUSer);
                        startActivity(Dr);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        Intent Account = new Intent(Voice.this, Account.class);

                         Account.putExtra("Email", GmailPageUSer);
                        startActivity(Account);
                        overridePendingTransition(0, 0);

                        return true;

                    case R.id.Logout:
                        Intent Logout = new Intent(Voice.this, sign_option.class);
                        Logout.putExtra("Email", GmailPageUSer);
                        startActivity(Logout);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;

            }

        });

        if (isMicrophonePresent()) {
            getMicrophonePermission();
        }



    }


    public void btnRecordPressed(View v) {
        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(getRecordingFilePath());
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
            mediaRecorder.start();
            Toast.makeText(this, "Rcording has started", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void btnStopPressed(View v) {
        try {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            Toast.makeText(this, "Rcording Stopped", Toast.LENGTH_LONG).show();

            postData();
            // "context" must be an Activity, Service or Application object from your app.


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnPlayPressed(View v) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getRecordingFilePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(this, "Rcording is Playing", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private boolean isMicrophonePresent() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            return true;
        } else {
            return false;
        }
    }

    private void getMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
        }

    }

    private String getRecordingFilePath() {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, filename);
        return file.getPath();
    }

    private void postData() {
        // on below line we are creating a retrofit
        // builder and passing our base url
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.24:5000/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create(gson))
                // at last we are building our retrofit builder.
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        VoiceClass modal = new VoiceClass(getRecordingFilePath());

        // calling a method to create a post and passing our modal class.
        Call<String> call = retrofitAPI.createPost(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code() + "\nMessage : " + responseFromAPI.toString();
               // textView.setText(responseString);
                // below line we are setting our
                // string to our text view.
                textView.setText(responseString);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
              textView.setText("Error found is : " + t.getMessage());
            }
        });

    }
}
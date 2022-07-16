package com.example.gp;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.gp.ml.ImageModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Image extends AppCompatActivity {
    private ImageButton captureImage,gallery;
    private TextView resultT;
    private TextView confidenceT;
    private ImageView imageView;
    int imageSize=48;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        captureImage = findViewById(R.id.capture_image);
        gallery=findViewById(R.id.gallary);
        resultT=findViewById(R.id.result);
        confidenceT=findViewById(R.id.confidence);
        imageView = findViewById(R.id.image_view);
        String GmailPageUSer = getIntent().getExtras().getString("Email");
        BottomNavigationView bottomNavigationView=findViewById(R.id.men);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String Mail_Text;

                switch (item.getItemId()) {
                    case R.id.home:
                        Intent Home = new Intent(Image.this, LevelsMenu.class);
                        Home.putExtra("Email", GmailPageUSer);
                        startActivity(Home);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Dr:
                        Intent Dr = new Intent(Image.this, DRCont.class);
                        Dr.putExtra("Email", GmailPageUSer);
                        startActivity(Dr);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        Intent Account = new Intent(Image.this, Account.class);

                         Account.putExtra("Email", GmailPageUSer);
                        startActivity(Account);
                        overridePendingTransition(0, 0);

                        return true;

                    case R.id.Logout:
                        Intent Logout = new Intent(Image.this, sign_option.class);
                        Logout.putExtra("Email", GmailPageUSer);
                        startActivity(Logout);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;

            }

        });


        if (isCameraPresent())
        {
            getCameraPermission();
        }
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });

        captureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();

            }
        });



    }

    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try{
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);

        }catch(ActivityNotFoundException e)
        {

        }
    }
    Bitmap image;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            image = (Bitmap) extras.get("data");
            imageView.setImageBitmap(image);

            int Dimensions = Math.min(image.getWidth(),image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image, Dimensions, Dimensions); // Center Crop

            image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);

            ClassifyFcialEmotion(image);
        }
        else if (resultCode==RESULT_OK && data!=null){
            Uri SelesctedImage=data.getData();
            imageView.setImageURI(SelesctedImage);
            Bitmap bitmap=null;
            try
            {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver() , Uri.parse(String.valueOf(SelesctedImage)));
            }
            catch (Exception e)
            {
                //handle exception
            }
            int Dimensions = Math.min(bitmap.getWidth(),bitmap.getHeight());
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, Dimensions, Dimensions); // Center Crop

            bitmap = Bitmap.createScaledBitmap(bitmap, imageSize, imageSize, false);

            ClassifyFcialEmotion(bitmap);
        }


    }
    public void ClassifyFcialEmotion(Bitmap image){
        try {

            ImageModel model = ImageModel.newInstance(this);

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 48, 48, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer=ByteBuffer.allocateDirect(4*imageSize*imageSize*1*3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int [] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            // iterate over pixels and extract R, G, and B values. Add to bytebuffer.
            int pixel=0;
            for(int i =0;i<imageSize;++i){
                for(int j=0;j<imageSize;++j){
                    final int val=intValues[pixel++];
                    // now put float value to bytebuffer
                    // scale image to convert image from 0-255 to 0-1
                    byteBuffer.putFloat((((val>>16)&0xFF))/255.0f); //R
                    byteBuffer.putFloat((((val>>8)&0xFF))/255.0f); //G
                    byteBuffer.putFloat(((val & 0xFF))/255.0f); //B

                }
            }
            inputFeature0.loadBuffer(byteBuffer);



            ImageModel.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
            String[] classes = {"Anger", "Disgust", "Fear", "Happy", "Neutral", "Sad", "Surprise"};
            float[] confidences = outputFeature0.getFloatArray();
            float  emotion_v=confidences[0];
            String val="";

           int maxPos = 0;
            float maxConfidence = 0;
            for(int i = 0; i < confidences.length; i++){
                if(confidences[i] > maxConfidence){
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
            confidenceT.setText(classes[maxPos]);

            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }
    private  boolean isCameraPresent()
    {
        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            return  true;
        }
        else

        {
            return false;
        }
    }
    private void getCameraPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==PackageManager.PERMISSION_DENIED)

        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},REQUEST_IMAGE_CAPTURE);
        }

    }



}
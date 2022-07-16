package com.example.gp;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitAPI {


    @POST("predict/")
    //on below line we are creating a method to post our data.
    Call<String> createPost(@Body VoiceClass dataModal);

}

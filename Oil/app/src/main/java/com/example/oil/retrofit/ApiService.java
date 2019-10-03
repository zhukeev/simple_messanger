package com.example.oil.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/post-api/firebase-id?")
    Call<ResponseBody> postId(@Query("id") String id);
/*
    @Headers({"Authorization: key=AAAAyZe3Syo:APA91bH4jHaWkhKpcKxuWgm3ijPpGKmW1OV7-5I9w2awxNN6-tSOs29JAeM-MXS8e-t4PRn_YjsXqq3UTAPX6e_pntswxeAzkvZlO16423gG2STHhPRj2SHlQLRBGT-d787b0Gz3UF5e",
            "Content-Type:application/json"})
    @POST("fcm/send")
    Call<ResponseBody> sendNotificationTo(@Body );*/
}

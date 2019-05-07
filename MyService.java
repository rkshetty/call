package com.example.tvapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

import com.example.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MyService extends Service {
    APIInterface apiInterface;
    Context context;

    //creating a mediaplayer object


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //getting systems default ringtone
        apiCall();

        //we have some options for service
        //start sticky means service will be explicity started and stopped
        return START_STICKY;
    }

    public void apiCall() {
        apiInterface = APIClient.getClient("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiI1YzhjYjljODk4ZjgxNTExNmI4ZjE4OTQiLCJleHBpcmVzIjoxNTU3NjYzNjk1NDU4LCJjb250ZXh0Ijp7Im5hbWUiOiJkb295YSIsInN1YmRvbWFpbiI6ImRvb3lhIiwicmVhZF9vbmx5IjpmYWxzZX19.yhIwTazbeTzl_UDsxqw8I7EDmScZ7YtxYY70KG5bR-g", Constants.KEY_CLIENT_TOKEN).create(APIInterface.class);
        Call<MoviesList> call1 = apiInterface.getHomePageChannels();
        call1.enqueue(new Callback<MoviesList>() {


            @Override
            public void onResponse(Call<MoviesList> call, retrofit2.Response<MoviesList> response) {
                try {

                    if (response.body() != null) {
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                call.cancel();

            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //stopping the player when service is destroyed
    }
}
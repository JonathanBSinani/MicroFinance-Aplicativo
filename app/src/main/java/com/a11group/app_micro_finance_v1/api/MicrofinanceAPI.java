package com.a11group.app_micro_finance_v1.api;

import android.content.Context;

import com.a11group.app_micro_finance_v1.R;
import com.a11group.app_micro_finance_v1.model.Login;
import com.a11group.app_micro_finance_v1.trait.MicrofinanceLoginInterface;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MicrofinanceAPI {

    private String BASE_URL;
    private Retrofit retrofit;

    public MicrofinanceAPI(Context context) {

        this.BASE_URL = context.getString(R.string.api_base_url);
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
    }

    public Call<Login> login(String email, String password) {

        return retrofit.create(MicrofinanceLoginInterface.class).login(email, password);
    }
}

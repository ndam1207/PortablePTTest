package com.example.hihihahahehe.portablept.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hihihahahehe on 8/17/17.
 */

public class RetrofitFactory {
    private static Retrofit retrofit;

    private static RetrofitFactory instance = new RetrofitFactory();

    public static RetrofitFactory getInstance() {
        return instance;
    }

    private RetrofitFactory(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://portablept.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }
}

package com.vaishnavi.telstratest.repository.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        fun getRetrofitInstance(): Api {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dl.dropboxusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            return retrofit.create(Api::class.java)
        }
    }
}
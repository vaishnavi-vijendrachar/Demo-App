package com.vaishnavi.telstratest.repository.remote

import com.vaishnavi.telstratest.model.Result
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("/s/2iodh4vg0eortkl/facts.js")
    fun getResultFromRemote() : Call<Result>
}
package com.vaishnavi.telstratest.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.vaishnavi.telstratest.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.vaishnavi.telstratest.model.Result

class Repository {

    fun getUserDataFromRemote(context : Context): MutableLiveData<Result> {
        var list = MutableLiveData<Result>()
        lateinit var res: Result
        val call = RetrofitClient.getRetrofitInstance().getResultFromRemote()
        call.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    if (response.isSuccessful) {
                        var body = response.body()
                        res = Result(body!!.title, body!!.rows)
                    }
                    list.value = res
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return list
    }
}
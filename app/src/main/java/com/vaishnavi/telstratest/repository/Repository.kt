package com.vaishnavi.telstratest.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vaishnavi.telstratest.model.Facts
import com.vaishnavi.telstratest.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.vaishnavi.telstratest.model.Result
import com.vaishnavi.telstratest.repository.local.FactsDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
                        saveDataInToDb(res,context)
                    }
                    list.value = res
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return list
    }

    fun saveDataInToDb(res: Result, context: Context) {
        GlobalScope.launch{
            var facts = res.rows
            for(fact : Facts in facts) {
                if(fact.title != null) {
                    FactsDatabase.getInstance(context).factsDao()
                        .insertFact(Facts(fact.title, fact.description, fact.imageHref))
                }
            }
        }
    }

    fun getDataFromDb(context : Context) : LiveData<List<Facts>> {
        var list : LiveData<List<Facts>> = MutableLiveData<List<Facts>>()
        runBlocking {
            launch{
                list = FactsDatabase.getInstance(context).factsDao().getFacts()
            }
        }
        return list
    }
}
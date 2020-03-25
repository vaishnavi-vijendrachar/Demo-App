package com.vaishnavi.telstratest.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vaishnavi.telstratest.model.Facts
import com.vaishnavi.telstratest.model.Result
import com.vaishnavi.telstratest.repository.Repository

class MainViewModel : ViewModel() {
    /**
     * get data from server and return the list back
     */
    fun getDataFromServer(context : Context) : MutableLiveData<Result> {
        return Repository().getUserDataFromRemote(context)
    }

    /**
     * get locally cached data and return the result back
     */
    fun getDataFromDb(applicationContext: Context): LiveData<List<Facts>> {
        return Repository().getDataFromDb(applicationContext)
    }


}

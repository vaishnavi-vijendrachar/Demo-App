package com.vaishnavi.telstratest.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vaishnavi.telstratest.model.Result
import com.vaishnavi.telstratest.repository.Repository

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    fun getDataFromRepository(applicationContext: Context) : MutableLiveData<Result> {
        return Repository().getUserDataFromRemote(applicationContext)
    }
}

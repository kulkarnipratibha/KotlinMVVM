package com.android.androidexercise.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.androidexercise.R
import com.android.androidexercise.model.ListModel
import com.android.androidexercise.repository.MainActivityRepo


class MainActivityViewModel(mApplication: Application) : AndroidViewModel(mApplication) {

    private val repository = MainActivityRepo(mApplication)
    val listData: LiveData<ListModel>
    val progressBar: LiveData<Boolean>
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    init {
        this.listData = repository.listData
        this.progressBar = repository.progressBar
    }

    fun fetchListData() {
        repository.fetchListData()
    }

    fun onRetrievePostListError() {
        errorMessage.value = R.string.strNoNetwork

    }

}
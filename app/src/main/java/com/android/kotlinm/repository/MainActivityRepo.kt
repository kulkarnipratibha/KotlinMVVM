package com.android.androidexercise.repository

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.android.androidexercise.networking.NetworkException
import com.android.androidexercise.networking.NetworkInterface
import com.android.androidexercise.networking.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.material.snackbar.Snackbar
import android.graphics.Color
import android.widget.TextView
import android.widget.Toast
import com.android.androidexercise.R
import com.android.androidexercise.model.ListModel

//Repository
class MainActivityRepo(val mApplication: Application) {

    val listData = MutableLiveData<ListModel>()
    val progressBar = MutableLiveData<Boolean>()

    fun fetchListData() {

        val retrofit = RetrofitAPI.getRetrofitClient(mApplication)

        val service = retrofit?.create(NetworkInterface::class.java)

        service?.getListData()?.enqueue(object : Callback<ListModel> {

            // show network error
            override fun onFailure(call: Call<ListModel>, t: Throwable) {

                if (t is NetworkException) {
                    progressBar.value = true
                    val toast =
                            Toast.makeText(
                                    mApplication,
                                    "Please check your Internet Connection",
                                    Toast.LENGTH_SHORT
                            )
                    toast.show()

                }
            }

            override fun onResponse(call: Call<ListModel>, response: Response<ListModel>) {
                if (response.body() != null)
                    listData.value = response.body()
            }
        })
    }


}
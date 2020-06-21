package com.android.androidexercise.networking


import com.android.androidexercise.model.ListModel
import retrofit2.http.GET

import okhttp3.ResponseBody
import retrofit2.Call

//The base URL of the API
const val BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"


internal interface NetworkInterface {

    @GET("facts.json")
    fun getListData(): Call<ListModel>


}
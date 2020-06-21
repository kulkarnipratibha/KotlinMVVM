package com.android.androidexercise.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Cache
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient


object RetrofitAPI {
    private var retrofit: Retrofit? = null

    fun getRetrofitClient(mContext: Context): Retrofit? {
        if (retrofit == null) {

            val oktHttpClient = OkHttpClient.Builder()
                    .addInterceptor(ConnectionInterceptor(mContext))

            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    // Adding our OkHttpClient
                    .client(oktHttpClient.build())
                    .build()

        }
        return retrofit
    }


}

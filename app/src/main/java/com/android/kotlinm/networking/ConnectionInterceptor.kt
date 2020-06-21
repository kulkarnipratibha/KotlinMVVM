package com.android.androidexercise.networking

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class ConnectionInterceptor(private val mContext: Context) : Interceptor {


    //checking connection
    private val isConnected: Boolean
        get() {
            val connectivityManager =
                    mContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected) {
            throw NetworkException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }




}

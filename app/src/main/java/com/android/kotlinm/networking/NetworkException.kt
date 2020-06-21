package com.android.androidexercise.networking

import java.io.IOException

class NetworkException : IOException() {

    override val message: String
        get() = "Please check your Internet Connection"
}
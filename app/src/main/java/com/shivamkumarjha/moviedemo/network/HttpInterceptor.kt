package com.shivamkumarjha.moviedemo.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HttpInterceptor(
    private val networkHelper: NetworkHelper,
    private val noConnectivityException: NoConnectivityException
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkHelper.isNetworkConnected())
            throw noConnectivityException
        val request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        val newRequest: Request = requestBuilder.build()
        return chain.proceed(newRequest)
    }
}
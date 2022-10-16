package com.example.android.politicalpreparedness.network.api

import com.example.android.politicalpreparedness.utils.CommonConstants
import okhttp3.OkHttpClient

class CivicsHttpClient : OkHttpClient() {

    companion object {
        fun getClient(): OkHttpClient {
            return Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val url = original
                        .url()
                        .newBuilder()
                        .addQueryParameter("key", CommonConstants.API_KEY)
                        .build()
                    val request = original
                        .newBuilder()
                        .url(url)
                        .build()
                    chain.proceed(request)
                }
                .build()
        }

    }

}
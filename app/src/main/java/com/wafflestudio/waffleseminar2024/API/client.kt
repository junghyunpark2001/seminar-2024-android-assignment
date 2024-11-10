package com.wafflestudio.waffleseminar2024.API

import android.util.Log
import okhttp3.OkHttpClient
import com.wafflestudio.waffleseminar2024.BuildConfig


val client = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val original = chain.request()
        val originalUrl = original.url

        // API 키가 제대로 추가되는지 확인
        val apiKey = BuildConfig.TMDB_API_KEY
        Log.d("API_KEY_DEBUG", "API Key in Interceptor: $apiKey")

        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()

        chain.proceed(request)
    }
    .build()

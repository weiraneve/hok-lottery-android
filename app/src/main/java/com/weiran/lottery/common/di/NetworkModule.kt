package com.weiran.lottery.common.di

import com.weiran.lottery.data.api.RetrofitApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single {
        retrofitClient.create(RetrofitApi::class.java)
    }
}

val okHttpClient: OkHttpClient = OkHttpClient.Builder().apply {
    connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
    readTimeout(TIME_OUT, TimeUnit.SECONDS)
    writeTimeout(TIME_OUT, TimeUnit.SECONDS)
}.build()

val retrofitClient: Retrofit = Retrofit.Builder()
    .baseUrl(RetrofitApi.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

private const val TIME_OUT = 5L
private const val CONNECT_TIME_OUT = 15L
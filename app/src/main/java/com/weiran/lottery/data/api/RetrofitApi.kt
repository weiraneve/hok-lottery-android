package com.weiran.lottery.data.api

import com.weiran.lottery.data.model.LotteryEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitApi {

    @POST("/")
    suspend fun getLottery(@Body key: String = ""): Response<LotteryEntity>

    companion object {
        const val BASE_URL = "https://steveay.com"
    }

}
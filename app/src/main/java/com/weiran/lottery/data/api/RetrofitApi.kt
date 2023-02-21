package com.weiran.lottery.data.api

import com.weiran.lottery.data.model.LotteryEntity
import com.weiran.lottery.data.model.PostParam
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitApi {

    @POST("/")
    @Headers("Content-Type: application/json;charset=utf-8")
    suspend fun fetchData(@Body postParam: PostParam): Response<LotteryEntity>

    companion object {
        const val BASE_URL = "http://steveay.com:8034"
    }

}
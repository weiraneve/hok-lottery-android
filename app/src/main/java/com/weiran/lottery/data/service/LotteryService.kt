package com.weiran.lottery.data.service

import com.weiran.lottery.common.exception.ApiException
import com.weiran.lottery.common.exception.NetworkReachableException
import com.weiran.lottery.common.obj.Result
import com.weiran.lottery.data.api.RetrofitApi
import com.weiran.lottery.data.model.LotteryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface LotteryService {
    suspend fun getLottery(): Flow<Result<LotteryEntity>>
}

class LotteryServiceImpl(private val lotteryApi: RetrofitApi) : LotteryService {

    override suspend fun getLottery(): Flow<Result<LotteryEntity>> = flow {
        emit(Result.Loading)
        emit(
            try {
                val response = lotteryApi.getLottery("demo")
                if (response.isSuccessful) {
                    Result.Success(response.body())
                } else {
                    Result.Error(ApiException())
                }
            } catch (error: NetworkReachableException) {
                Result.Error(error)
            } catch (throwable: Throwable) {
                Result.Error(ApiException())
            }
        )
    }
}
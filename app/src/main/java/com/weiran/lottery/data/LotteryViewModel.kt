package com.weiran.lottery.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiran.lottery.common.exception.NetworkReachableException
import com.weiran.lottery.common.obj.Result
import com.weiran.lottery.data.service.LotteryService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LotteryViewModel(private val lotteryService: LotteryService) : ViewModel() {

    private val _lotteryState = MutableStateFlow(LotteryState())
    val lotteryState = _lotteryState.asStateFlow()

    private fun fetchLottery() {
        viewModelScope.launch {
            lotteryService.getLottery().collect { res ->
                _lotteryState.update {
                    val result = when (res) {
                        is Result.Loading -> LOADING
                        is Result.Success -> res.data?.toString()
                        is Result.Error -> {
                            when (res.exception) {
                                is NetworkReachableException -> res.exception.message
                                else -> ERROR
                            }
                        }
                    }
                    it.copy(data = result)
                }

            }
        }
    }

    fun dispatchAction(action: LotteryAction) {
        when (action) {
            LotteryAction.FetchLottery -> fetchLottery()
        }
    }

    companion object {
        const val LOADING = "Loading"
        const val ERROR = "Error"
    }
}

data class LotteryState(
    val data: String? = ""
)

sealed class LotteryAction {
    object FetchLottery : LotteryAction()
}
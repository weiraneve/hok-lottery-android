package com.weiran.lottery.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiran.lottery.common.exception.NetworkReachableException
import com.weiran.lottery.common.obj.Result
import com.weiran.lottery.data.model.LogResponse
import com.weiran.lottery.data.service.LotteryService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

class LotteryViewModel(private val lotteryService: LotteryService) : ViewModel() {

    private val _lotteryState = MutableStateFlow(LotteryState())
    val lotteryState = _lotteryState.asStateFlow()

    private fun fetchLottery(encryptCode: String) {
        viewModelScope.launch {
            lotteryService.getLottery(encryptCode).collect { res ->
                _lotteryState.update {
                    val data: String
                    var logs = ""
                    when (res) {
                        is Result.Loading -> data = LOADING
                        is Result.Success -> {
                            data = res.data?.data.toString()
                            logs = getLogs(res.data?.logs)
                        }

                        is Result.Error -> {
                            data = when (res.exception) {
                                is NetworkReachableException -> res.exception.message.toString()
                                else -> ERROR
                            }
                        }
                    }
                    it.copy(data = data, logsResult = logs, isRequest = true)
                }
            }
        }
    }

    private fun getLogs(logs: List<LogResponse>?): String {
        var logsResult = ""
        logs?.forEach {
            logsResult += "队伍: " + it.teamId.toString() + " " + it.pickGroup + " 时间: " + it.time.toLocaleString() + "\n\n"
        }
        return logsResult
    }

    fun dispatchAction(action: LotteryAction) {
        when (action) {
            is LotteryAction.FetchLottery -> fetchLottery(action.encryptCode.lowercase(Locale.getDefault()))
        }
    }

    companion object {
        const val LOADING = "Loading"
        const val ERROR = "Error"
    }
}

data class LotteryState(
    val data: String = "",
    val logsResult: String = "",
    val isRequest: Boolean = false
)

sealed class LotteryAction {
    data class FetchLottery(val encryptCode: String) : LotteryAction()
}
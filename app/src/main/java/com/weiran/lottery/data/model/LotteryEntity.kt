package com.weiran.lottery.data.model

data class LotteryEntity(
    val teamId: String,
    val data: String,
    val time: String,
    val logs: List<LogResponse>? = null
)
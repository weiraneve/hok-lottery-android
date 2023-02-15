package com.weiran.lottery.data.model

import java.util.Date

data class LogResponse(
    var teamId: Int? = null,
    var pickGroup: String = "",
    var time: Date = Date()
)
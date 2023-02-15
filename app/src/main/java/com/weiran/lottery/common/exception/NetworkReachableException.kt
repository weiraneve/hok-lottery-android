package com.weiran.lottery.common.exception

import java.io.IOException

class NetworkReachableException(msg: String? = "network is unreachable") : IOException(msg)
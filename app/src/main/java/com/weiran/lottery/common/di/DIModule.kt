package com.weiran.lottery.common.di

import com.weiran.lottery.data.LotteryViewModel
import com.weiran.lottery.data.service.LotteryService
import com.weiran.lottery.data.service.LotteryServiceImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LotteryViewModel(get()) }
}

val serviceModule = module {
    single<LotteryService> { LotteryServiceImpl(get()) }
}
package com.example.hellokmp.di

import com.example.hellokmp.BatteryManager
import com.example.hellokmp.BatteryManagerImpl
import com.example.hellokmp.dependencies.DbClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::DbClient)
    singleOf(::BatteryManagerImpl).bind<BatteryManager>()
}
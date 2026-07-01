package com.example.hellokmp.di

import com.example.hellokmp.BatteryManager
import com.example.hellokmp.BatteryManagerImpl
import com.example.hellokmp.dependencies.DbClient
import com.example.hellokmp.dependencies.TestViewModel
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::DbClient)
    viewModelOf(::TestViewModel)
    singleOf(::BatteryManagerImpl).bind<BatteryManager>()
    single { Darwin.create() }
}
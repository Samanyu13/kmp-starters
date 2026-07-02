package com.example.hellokmp.di

import com.example.hellokmp.dependencies.Repo
import com.example.hellokmp.dependencies.RepoImpl
import com.example.hellokmp.network.InsultCensorClient
import com.example.hellokmp.network.createHttpClient
import com.example.hellokmp.presentation.CensorViewModel
import com.example.hellokmp.presentation.DataStoreViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    /* This is one another way
    single {
        RepoImpl(get())
    }.bind<Repo>()
     */

    singleOf(::RepoImpl).bind<Repo>()
    single { createHttpClient(get()) }
    singleOf(::InsultCensorClient)
    viewModelOf(::CensorViewModel)
    viewModelOf(::DataStoreViewModel)
}
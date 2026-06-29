package com.example.hellokmp.dependencies

import androidx.lifecycle.ViewModel

class TestViewModel(
    private val repo: Repo
): ViewModel() {

    fun getHelloWorld(): String = repo.helloWorld()
}
package com.example.hellokmp.dependencies

interface Repo {
    fun helloWorld(): String
}

class RepoImpl(
    private val dbClient: DbClient
) : Repo {
    override fun helloWorld(): String {
        return "Hello World!"
    }

}
package com.example.koin.model.repository

import com.example.koin.model.web.GithubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val api: GithubApi) {
    fun getAllUsers() = api.getUsers()
}
package com.example.koin.model.web

import com.example.koin.model.room.GithubUser
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    fun getUsers(): Call<List<GithubUser>>
}
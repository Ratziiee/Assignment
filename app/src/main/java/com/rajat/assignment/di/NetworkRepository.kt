package com.rajat.assignment.di

import com.rajat.assignment.api.Api
import com.rajat.assignment.models.Posts
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(val api : Api) {

    suspend fun getPosts() : Response<Posts> {
        return api.getPosts()
    }
}
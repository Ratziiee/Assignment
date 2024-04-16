package com.rajat.assignment.api

import com.rajat.assignment.models.Posts
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("posts")
    suspend fun getPosts() : Response<Posts>
}
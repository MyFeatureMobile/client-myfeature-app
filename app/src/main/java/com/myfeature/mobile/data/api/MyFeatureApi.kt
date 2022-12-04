package com.myfeature.mobile.data.api

import com.myfeature.mobile.data.model.CreatePostParams
import com.myfeature.mobile.data.model.CreatePostResponse
import com.myfeature.mobile.data.model.CreateUserParams
import com.myfeature.mobile.data.model.UserResponse
import com.myfeature.mobile.domain.model.UserProfile
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MyFeatureApi {

  @POST("feature")
  fun createPost(@Body post: CreatePostParams): CreatePostResponse

  @GET("user/{user}")
  fun getProfile(@Query("user") userId: String): UserProfile

  @POST("users")
  fun createUser(@Body user: CreateUserParams): UserResponse
}
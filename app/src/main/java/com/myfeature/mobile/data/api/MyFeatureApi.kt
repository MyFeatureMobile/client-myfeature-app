package com.myfeature.mobile.data.api

import com.myfeature.mobile.data.model.AuthParams
import com.myfeature.mobile.data.model.AuthResponse
import com.myfeature.mobile.data.model.AvatarParams
import com.myfeature.mobile.data.model.CreatePostParams
import com.myfeature.mobile.data.model.CreatePostResponse
import com.myfeature.mobile.data.model.CreateUserParams
import com.myfeature.mobile.data.model.UpdateDescriptionParams
import com.myfeature.mobile.data.model.feature.LoadedPhoto
import com.myfeature.mobile.data.model.feature.PhotoUrl
import com.myfeature.mobile.data.model.UserResponse
import com.myfeature.mobile.data.model.feature.FeatureResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyFeatureApi {

  @POST("features")
  suspend fun createPost(@Body post: CreatePostParams): CreatePostResponse

  @GET("features")
  suspend fun getPosts(): List<FeatureResponse>

  @GET("users/{user}")
  suspend fun getProfile(@Path("user") userId: Long): UserResponse

  @POST("users")
  suspend fun createUser(@Body user: CreateUserParams): UserResponse

  @POST("photos")
  suspend fun loadPhoto(@Body url: PhotoUrl): LoadedPhoto

  @GET("photo/{id}")
  suspend fun getPhotoById(@Path("id") id: Long): PhotoUrl

  @POST("users/desc/{id}")
  suspend fun updateDescription(@Path("id") userId: Long, @Body description: UpdateDescriptionParams)

  @POST("users/photo/{id}")
  suspend fun updateUserAvatar(@Path("id") userId: Long, @Body photoId: AvatarParams)

  @POST("users/access")
  suspend fun authorize(@Body data: AuthParams): UserResponse
}
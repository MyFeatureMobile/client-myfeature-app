package com.myfeature.mobile.data.model.feature

import com.google.gson.annotations.SerializedName

data class FeatureResponse(
  @SerializedName("feature_id") var featureId: Long? = null,
  @SerializedName("name") var name: String? = null,
  @SerializedName("user") var user: User? = User(),
  @SerializedName("photo") var photo: Photo? = Photo(),
  @SerializedName("content") var content: String? = null,
  @SerializedName("created") var created: String? = null
)
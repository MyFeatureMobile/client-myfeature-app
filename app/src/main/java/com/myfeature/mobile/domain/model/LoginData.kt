package com.myfeature.mobile.domain.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class LoginData(
  val userName: String,
  val password: String
) : Parcelable {

  constructor(parcel: Parcel) : this(
    parcel.readString() ?: "",
    parcel.readString() ?: ""
  ) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(userName)
    parcel.writeString(password)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<LoginData> {
    override fun createFromParcel(parcel: Parcel): LoginData {
      return LoginData(parcel)
    }

    override fun newArray(size: Int): Array<LoginData?> {
      return arrayOfNulls(size)
    }
  }
}
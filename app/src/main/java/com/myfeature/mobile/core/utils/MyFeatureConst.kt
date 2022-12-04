package com.myfeature.mobile.core.utils

import com.myfeature.mobile.core.utils.DataSourceStrategy.MIX_DATA_USED
import com.myfeature.mobile.core.utils.DataSourceStrategy.MOCKED_DATA_USED

const val API_URL = "https://my-feature-app.herokuapp.com/api/v1/"

val dataStrategy = MOCKED_DATA_USED

enum class DataSourceStrategy {
  REAL_DATA_USED,
  MOCKED_DATA_USED,
  MIX_DATA_USED
}
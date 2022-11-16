package com.myfeature.mobile.core.coroutines

import kotlinx.coroutines.Dispatchers

class AppDispatchers {

  fun io() = Dispatchers.IO

  fun main() = Dispatchers.Main

  fun default() = Dispatchers.Default
}
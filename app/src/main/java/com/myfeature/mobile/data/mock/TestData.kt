package com.myfeature.mobile.data.mock

import com.myfeature.mobile.domain.model.UserProfile
import com.myfeature.mobile.ui.home.post.model.PostModel
import com.myfeature.mobile.ui.home.post.model.UserOnPost
import com.myfeature.mobile.ui.home.profile.model.PostItem

object TestData {

  val avatarDefault =
    "https://image.winudf.com/v2/image1/b3JnLm5ld3dhbGwuY2F0ZG9nX3NjcmVlbl80XzE1NzQyNTE2MDZfMDkw/screen-4.jpg?fakeurl=1&type=.jpg"

  val photos = listOf(
    "https://img.freepik.com/free-vector/smart-home-application_23-2148627263.jpg?w=2000&t=st=1668187263~exp=1668187863~hmac=fc502f8163dd1d2d50c6f9ad3ad88ced7ada33e7b5ca7ec646fdb538882a2a44",
    "https://i.pinimg.com/originals/d1/f4/30/d1f430b2df300c3d8d00d697aae04185.jpg"
  )

  val posts = listOf<PostItem>(
    PostItem(
      id = "2".toLong(),
      photoUrls = listOf("https://img.freepik.com/free-vector/smart-home-application_23-2148627263.jpg?w=2000&t=st=1668187263~exp=1668187863~hmac=fc502f8163dd1d2d50c6f9ad3ad88ced7ada33e7b5ca7ec646fdb538882a2a44"),
      4
    ),
    PostItem(
      id = "2".toLong(),
      photoUrls = listOf("https://img.freepik.com/free-vector/smart-home-application_23-2148627263.jpg?w=2000&t=st=1668187263~exp=1668187863~hmac=fc502f8163dd1d2d50c6f9ad3ad88ced7ada33e7b5ca7ec646fdb538882a2a44"),
      4
    ),
    PostItem(
      id = "2".toLong(),
      photoUrls = listOf("https://img.freepik.com/free-vector/smart-home-application_23-2148627263.jpg?w=2000&t=st=1668187263~exp=1668187863~hmac=fc502f8163dd1d2d50c6f9ad3ad88ced7ada33e7b5ca7ec646fdb538882a2a44"),
      4
    ),
  )

  val users = listOf(
    UserOnPost(
      userId = "3".toLong(),
      nickname = "elena_elenovna",
      userPhotoUrl = "https://cdn.thetealmango.com/wp-content/uploads/2022/02/yael-shelbia-pic.jpg"
    ),
    UserOnPost(
      userId = "3".toLong(),
      nickname = "elena_elenovna",
      userPhotoUrl = "https://cdn.thetealmango.com/wp-content/uploads/2022/02/yael-shelbia-pic.jpg"
    ),
    UserOnPost(
      userId = "3".toLong(),
      nickname = "elena_elenovna",
      userPhotoUrl = "https://cdn.thetealmango.com/wp-content/uploads/2022/02/yael-shelbia-pic.jpg"
    ),
    UserOnPost(
      userId = "3".toLong(),
      nickname = "elena_elenovna",
      userPhotoUrl = "https://cdn.thetealmango.com/wp-content/uploads/2022/02/yael-shelbia-pic.jpg"
    )
  )

  val post = PostModel(
    id = "2".toLong(),
    photoUrls = photos,
    likesCount = 1234,
    liked = false,
    userOnPost = users.first(),
    description = "Hi, everyone, this is my first post on MyFeature\n" +
        "Follow me on TikTok :)",
    codeLink = "https://github.com/MyFeatureMobile/client-myfeature-app/blob/master/app/src/main/java/com/myfeature/mobile/ui/home/HomeScreens.kt",
  )

  val userProfile = UserProfile(
    userId = 12345678,
    avatarUrl = "https://wallbox.ru/wallpapers/main/201151/koshki-f60c1e13bc32.jpg",
    userName = "Simon Kulyomin",
    description = "My features are the best in the world of Mobile Development!",
    email = "simon@kulyomin.dev",
    postsCount = 63,
    followersCount = 734,
    followingCount = 5223
  )
}
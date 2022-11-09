package com.myfeature.mobile.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection.Ltr
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.myfeature.mobile.core.theme.AppTheme
import com.myfeature.mobile.core.theme.backgroundBottomBar
import com.myfeature.mobile.core.theme.bgMain
import com.myfeature.mobile.core.theme.controlMain
import com.myfeature.mobile.core.utils.Functions

@Composable
fun Home(onLogOut: () -> Unit = Functions::empty) {
  AppTheme {
    val appState = rememberAppState()
    Scaffold(
      bottomBar = {
        BottomBar(navController = appState.navController)
      }
    ) {
      HomeNavGraph(
        navController = appState.navController,
        modifier = Modifier
          .padding(
            start = it.calculateStartPadding(Ltr),
            end = it.calculateEndPadding(Ltr),
            top = it.calculateTopPadding(),
            bottom = it.calculateBottomPadding()
          )
          .background(MaterialTheme.colors.bgMain)
      )
    }
  }
}

@Composable
fun BottomBar(navController: NavHostController) {
  val screens = HomeScreens.values()
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination

  val bottomBarDestination = screens.any { it.route == currentDestination?.route }
  if (bottomBarDestination) {
    val shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
    BottomNavigation(
      backgroundColor = MaterialTheme.colors.backgroundBottomBar,
      modifier = Modifier
        .clip(shape)
        .shadow(elevation = 10.dp, shape = shape)
    ) {
      screens.forEach { screen ->
        AddItem(
          screen = screen,
          currentDestination = currentDestination,
          navController = navController
        )
      }
    }
  }
}

@Composable
fun RowScope.AddItem(
  screen: HomeScreens,
  currentDestination: NavDestination?,
  navController: NavHostController
) {
  BottomNavigationItem(
    icon = {
      Icon(
        imageVector = screen.icon,
        contentDescription = "Navigation Icon",
        tint = MaterialTheme.colors.controlMain
      )
    },
    selected = currentDestination?.hierarchy?.any {
      it.route == screen.route
    } == true,
    unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
    onClick = {
      navController.navigate(screen.route) {
        popUpTo(navController.graph.findStartDestination().id)
        launchSingleTop = true
      }
    }
  )
}

@Composable
fun rememberAppState(
  scaffoldState: ScaffoldState = rememberScaffoldState(), navController: NavHostController = rememberNavController()
) = remember(scaffoldState, navController) {
  AppState(scaffoldState, navController)
}

@Preview
@Composable
fun HomePreview() {
  Home()
}
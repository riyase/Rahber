package com.rahbertheadvisor.android

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rahbertheadvisor.android.common.Destination
import com.rahbertheadvisor.android.dashboard.model.Course
import com.rahbertheadvisor.android.dashboard.view.AllCourses
import com.rahbertheadvisor.android.dashboard.view.CourseDetails
import com.rahbertheadvisor.android.dashboard.view.Explore
import com.rahbertheadvisor.android.dashboard.view.InterestsScreen
import com.rahbertheadvisor.android.dashboard.view.MyCourses
import com.rahbertheadvisor.android.ui.BottomNavItem
import com.rahbertheadvisor.android.ui.BottomNavigationBar
import com.rahbertheadvisor.android.ui.theme.RahberTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RahberTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                // State of bottomBar, set state to false, if current page route is "car_details"
                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController, bottomBarState) }

                ) { innerPadding ->
                    NavHost(navController, startDestination = BottomNavItem.AllCourses.route, Modifier.padding(innerPadding)) {
                        composable(BottomNavItem.AllCourses.route) {
                            AllCourses(navController = navController)
                            bottomBarState.value = true
                        }
                        composable(BottomNavItem.MyCourses.route) {
                            MyCourses(navController = navController)
                            bottomBarState.value = true
                        }
                        composable(BottomNavItem.Recommended.route) {
                            Explore(navController = navController)
                            bottomBarState.value = true
                        }
                        composable(Destination.UserInterests.route) {
                            InterestsScreen(navController = navController)
                            bottomBarState.value = false
                        }
                        composable(Destination.CourseDetails.route, arguments = listOf(navArgument("id") { type = NavType.IntType })) { navEntry ->
                            navEntry.arguments?.getInt("id")?.let { id ->
                                CourseDetails(id = id)
                                bottomBarState.value = false
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RahberTheme {
        Greeting("Android")
    }
}

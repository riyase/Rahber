package com.rahbertheadvisor.android.dashboard.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rahbertheadvisor.android.dashboard.view.components.CourseItem
import com.rahbertheadvisor.android.dashboard.viewmodel.MyCoursesViewModel


@Composable
fun MyCourses(navController: NavHostController,
               viewModel: MyCoursesViewModel = hiltViewModel()
) {

    val courses by viewModel.myCourses.collectAsState()

    Surface {
        if (courses.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "No enrolled courses!",
                    modifier = Modifier.align(Alignment.Center))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(5.dp, 5.dp)
                    .fillMaxSize()
            ) {
                items(courses) { course ->
                    CourseItem(course = course, navController = navController)
                }
            }
        }
    }
}
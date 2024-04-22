package com.rahbertheadvisor.android.dashboard.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.rahbertheadvisor.android.common.Destination
import com.rahbertheadvisor.android.common.model.Result
import com.rahbertheadvisor.android.dashboard.model.Course
import com.rahbertheadvisor.android.dashboard.view.components.CourseItem
import com.rahbertheadvisor.android.dashboard.viewmodel.AllCoursesViewModel

@Composable
fun AllCourses(navController: NavHostController,
               viewModel: AllCoursesViewModel = hiltViewModel()) {

    val courses by viewModel.allCourses.collectAsState()

    Surface {
        when(courses) {
            is Result.Loading -> {
                Text("Loading")
            }
            is Result.Error -> {
                Text("Error: ${(courses as Result.Error).message}")
            }
            is Result.Success -> {
                LazyColumn(modifier = Modifier
                    .padding(5.dp, 5.dp)
                    .fillMaxWidth()) {
                    items((courses as Result.Success<List<Course>>).data) { course ->
                        CourseItem(course = course, navController = navController)
                    }
                }
            }
            else -> {
                Text("Idle")
            }
        }
    }
}
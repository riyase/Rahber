package com.rahbertheadvisor.android.dashboard.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.rahbertheadvisor.android.common.model.Result
import com.rahbertheadvisor.android.dashboard.model.Course
import com.rahbertheadvisor.android.dashboard.view.components.VideoModule
import com.rahbertheadvisor.android.dashboard.viewmodel.AllCoursesViewModel

@Composable
fun CourseDetails(id: Int, viewModel: AllCoursesViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.getCourse(id)
    }

    val courseRes by viewModel.course.collectAsState()
    Scaffold(bottomBar = {}) { padding ->
        Surface {
            when(courseRes) {
                is Result.Loading -> {
                    Text("Loading")
                }
                is Result.Error -> {
                    Text("Error: ${(courseRes as Result.Error).message}")
                }
                is Result.Success -> {
                    val course = (courseRes as Result.Success<Course>).data
                    Details(viewModel, course)
                }
                else -> {
                    Text("Idle")
                }
            }
        }
    }

}

@Composable fun Details(viewModel: AllCoursesViewModel, course: Course) {

    val isEnrolled by viewModel.enrollStatus.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(modifier = Modifier.padding(bottom = 80.dp)) {

            item {
                Column(modifier = Modifier.padding(bottom = 0.dp)) {
                    AsyncImage(
                        fallback = rememberVectorPainter(image = Icons.Default.FavoriteBorder),
                        model = course.thumbUrl,
                        contentDescription = course.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .align(Alignment.CenterHorizontally),
                        contentScale = ContentScale.Crop
                    )
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(0.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            Text(
                                text = course.title,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                                    .padding(10.dp)
                            )
                            Text(
                                text = course.longDescription,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                                    .padding(10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                    )
                    Text(
                        text = "Video modules",
                        fontSize = 18.sp,
                        style = TextStyle(color = Color.DarkGray),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            items(course.videoModules) { item ->
                VideoModule(videoModule = item)
            }

        }

        Button(modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .padding(10.dp)
            .align(Alignment.BottomCenter),
            onClick = {
                if (isEnrolled) {
                    viewModel.disEnrollCourse(course)
                } else {
                    viewModel.enrollCourse(course)
                }
        }) {
            val label = if (isEnrolled) "Dis Enroll" else "Enroll"
            Text(text = label)
        }
    }

}
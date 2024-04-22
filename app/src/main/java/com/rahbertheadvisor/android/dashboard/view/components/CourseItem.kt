package com.rahbertheadvisor.android.dashboard.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.rahbertheadvisor.android.common.Destination
import com.rahbertheadvisor.android.dashboard.model.Course

@Composable
fun CourseItem(course: Course, navController: NavHostController) {
    Card(modifier = Modifier
        .padding(5.dp, 5.dp)
        .fillMaxWidth()
        .clickable {
            navController.navigate(
                Destination.CourseDetails.route.replace(
                    oldValue = "{id}",
                    newValue = course.id.toString()
                )
            )
        },
        shape = RoundedCornerShape(0.dp),) {
        Column {
            Row {
                AsyncImage(
                    model = course.thumbUrl,
                    contentDescription = course.title,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(10.dp),
                    contentScale = ContentScale.Crop)
                Column(modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                    Text(text = course.title,
                        modifier = Modifier,
                    )
                    Text(text = course.instructor,
                        modifier = Modifier,
                    )
                }

            }
            Text(text = course.shortDescription,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp))
        }


    }
}
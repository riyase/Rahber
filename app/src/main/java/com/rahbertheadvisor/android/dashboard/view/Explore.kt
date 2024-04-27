package com.rahbertheadvisor.android.dashboard.view

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rahbertheadvisor.android.common.Destination
import com.rahbertheadvisor.android.dashboard.view.components.CourseItem
import com.rahbertheadvisor.android.dashboard.viewmodel.MyCoursesViewModel

@Composable
fun Explore(navController: NavHostController,
            viewModel: MyCoursesViewModel = hiltViewModel()
) {

    val recommended by viewModel.recommended.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.fetchRecommended()
    }
    Scaffold(
        //floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Destination.UserInterests.route)
                    Toast.makeText(context, "Heyaaa!", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(Icons.Filled.FavoriteBorder,"Interests")
            }
        }
    ) { padding ->
        Surface {
            if (recommended.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "No recommended courses!",
                        modifier = Modifier.align(Alignment.Center))
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(5.dp, 5.dp)
                        .fillMaxSize()
                ) {
                    items(recommended) { course ->
                        CourseItem(course = course, navController = navController)
                    }
                }
            }
        }
    }

}
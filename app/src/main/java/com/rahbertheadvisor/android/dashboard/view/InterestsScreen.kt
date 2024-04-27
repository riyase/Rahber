package com.rahbertheadvisor.android.dashboard.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rahbertheadvisor.android.dashboard.viewmodel.MyCoursesViewModel

@Composable
fun InterestsScreen(navController: NavController, viewModel: MyCoursesViewModel = hiltViewModel()) {

    val TAG = "InterestsScreen"
    LaunchedEffect(Unit) {
        viewModel.getInterests()
    }

    val interests by viewModel.interests.collectAsState()

    Log.d(TAG, "onRender()")
    Scaffold { padding ->
        Box (modifier = Modifier
            .padding(padding)
            .fillMaxSize()){
            LazyColumn(modifier = Modifier
                .padding(bottom = 70.dp)
                .fillMaxWidth()) {
                itemsIndexed(interests) { index, item ->
                    var icon = Icons.Default.FavoriteBorder
                    var description = "Not Selected"
                    var tint = Color.Gray
                    if (item.active) {
                        icon = Icons.Default.Favorite
                        description = "Selected"
                        tint = Color.Green
                    }
                    Row(modifier = Modifier
                        .padding(14.dp, 4.dp)
                        .height(50.dp).fillMaxWidth()
                        .clickable {
                            viewModel.toggleInterest(index)
                        }
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(text = item.name)
                        Icon(imageVector = icon,
                            contentDescription = description,
                            tint = tint,
                            modifier = Modifier.size(20.dp))
                    }
                }
            }
            Button(onClick = {  
                viewModel.saveInterests()
                navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(50.dp)) {
                Text(text = "Save")
            }
        }

    }
}
package com.rahbertheadvisor.android.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object AllCourses : BottomNavItem("all_courses", Icons.Default.Home, "All Courses")
    object MyCourses : BottomNavItem("my_courses", Icons.Default.Face, "My Courses")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}
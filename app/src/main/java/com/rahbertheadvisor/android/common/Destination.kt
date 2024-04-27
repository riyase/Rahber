package com.rahbertheadvisor.android.common

sealed class Destination(val route: String) {
    data object CourseDetails: Destination("courseDetails?id={id}")
    data object UserInterests: Destination("userInterests")
}
package com.rahbertheadvisor.android.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import com.rahbertheadvisor.android.dashboard.model.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyCoursesViewModel @Inject constructor(
    val repository: CourseRepository
): ViewModel() {

    val myCourses = repository.getEnrolledCourses()
}
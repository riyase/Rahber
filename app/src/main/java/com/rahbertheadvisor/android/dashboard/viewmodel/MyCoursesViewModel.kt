package com.rahbertheadvisor.android.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahbertheadvisor.android.dashboard.model.Course
import com.rahbertheadvisor.android.dashboard.model.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCoursesViewModel @Inject constructor(
    val repository: CourseRepository
): ViewModel() {

    val myCourses = repository.getEnrolledCourses()

    val _recommended = MutableStateFlow<List<Course>>(listOf())
    val recommended = _recommended.asStateFlow()

    fun fetchRecommended() {
        viewModelScope.launch(Dispatchers.IO) {
            _recommended.value = repository.getRecommneded()
        }

    }

}
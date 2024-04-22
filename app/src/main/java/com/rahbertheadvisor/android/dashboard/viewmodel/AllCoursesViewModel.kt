package com.rahbertheadvisor.android.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahbertheadvisor.android.dashboard.model.Course
import com.rahbertheadvisor.android.common.model.Result
import com.rahbertheadvisor.android.dashboard.model.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCoursesViewModel @Inject constructor(
    val repository: CourseRepository
): ViewModel() {

    private val _allCourses = MutableStateFlow<Result<List<Course>>>(Result.Idle)
    val allCourses = _allCourses.asStateFlow()

    private val _course = MutableStateFlow<Result<Course>>(Result.Idle)
    val course = _course.asStateFlow()

    private val _enrollStatus = MutableStateFlow(false)
    val enrollStatus = _enrollStatus.asStateFlow()

    init {
        fetchCourses()
    }

    private fun fetchCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            _allCourses.value = Result.Success(repository.getCourses())
        }
    }

    fun getCourse(id: Int) {
        _course.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val course = repository.getCourse(id)
            if (course == null) {
                _course.value = Result.Error(Exception("Undefined"), "No course found")
            } else {
                _course.value = Result.Success(course)
                _enrollStatus.value = repository.isEnrolled(course)
            }
        }
    }

    fun enrollCourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.enrollCourse(course)
            _enrollStatus.value = true
        }
    }

    fun disEnrollCourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.disEnrollCourse(course)
            _enrollStatus.value = false
        }
    }

}
package com.rahbertheadvisor.android.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahbertheadvisor.android.dashboard.model.Course
import com.rahbertheadvisor.android.dashboard.model.Interest
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
    private val repository: CourseRepository
): ViewModel() {

    val myCourses = repository.getEnrolledCourses()

    private val _interests = MutableStateFlow<List<Interest>>(listOf())
    val interests = _interests.asStateFlow()

    private val _recommended = MutableStateFlow<List<Course>>(listOf())
    val recommended = _recommended.asStateFlow()

    fun fetchRecommended() {
        viewModelScope.launch(Dispatchers.IO) {
            _recommended.value = repository.getRecommneded()
        }

    }

    fun getInterests() {
        _interests.value = repository.getInterests()
    }

    fun toggleInterest(position: Int) {
        val updated = mutableListOf<Interest>()
        updated.addAll(_interests.value)
        updated[position] = updated[position].copy(active = !updated[position].active)
        _interests.value = updated
    }

    fun saveInterests() {
        repository.saveInterests(_interests.value)
    }


}
package com.rahbertheadvisor.android.dashboard.model.repository

import com.rahbertheadvisor.android.dashboard.model.Course
import kotlinx.coroutines.flow.StateFlow

interface CourseRepository {

    suspend fun getCourse(id: Int): Course?
    suspend fun getCourses(): List<Course>
    suspend fun enrollCourse(course: Course)
    suspend fun disEnrollCourse(course: Course)
    suspend fun isEnrolled(course: Course): Boolean
    fun getEnrolledCourses(): StateFlow<List<Course>>
    fun getRecommneded(): List<Course>

}
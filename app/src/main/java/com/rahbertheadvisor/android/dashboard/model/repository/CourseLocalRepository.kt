package com.rahbertheadvisor.android.dashboard.model.repository

import com.rahbertheadvisor.android.dashboard.model.Course
import kotlinx.coroutines.flow.StateFlow

class CourseLocalRepository: CourseRepository {

    override suspend fun getCourse(id: Int): Course? {
        return DummyData.getCourse(id)
    }

    override suspend fun getCourses(): List<Course> {
        return DummyData.getCourses()
    }

    override suspend fun enrollCourse(course: Course) {
        DummyData.enroll(course)
    }

    override suspend fun disEnrollCourse(course: Course) {
        DummyData.disEnroll(course)
    }

    override suspend fun isEnrolled(course: Course): Boolean {
        return DummyData.isEnrolled(course)
    }

    override fun getEnrolledCourses(): StateFlow<List<Course>> {
        return DummyData.getEnrolledCourses()
    }


}
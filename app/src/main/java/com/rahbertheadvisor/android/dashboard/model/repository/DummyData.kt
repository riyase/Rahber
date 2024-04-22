package com.rahbertheadvisor.android.dashboard.model.repository

import com.rahbertheadvisor.android.dashboard.model.Course
import com.rahbertheadvisor.android.dashboard.model.VideoModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object DummyData {

    val videoModules = listOf(
        VideoModule(
            name = "Big Buck Bunny",
            thumbUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/BigBuckBunny.jpg",
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        ),
        VideoModule(
            name = "Elephant Dream",
            thumbUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ElephantsDream.jpg",
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
        ),
        VideoModule(
            name = "For Bigger Blazes",
            thumbUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerBlazes.jpg",
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
        ),
        VideoModule(
            name = "For Bigger Escape",
            thumbUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerEscapes.jpg",
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"
        ),
        VideoModule(
            name = "For Bigger Fun",
            thumbUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerFun.jpg",
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"
        ),
    )
    private val _courses = listOf(
        Course(
            id = 1,
            title = "The MERN Fullstack Guide",
            thumbUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ElephantsDream.jpg",
            shortDescription = "Build fullstack React.js applications with Node.js, Express.js & MongoDB (MERN) with this project-focused course.",
            longDescription = """
                We built the bestselling React course on Udemy - this course now allows you to take your React knowledge to the next level and build fullstack web apps based on React, NodeJS, MongoDB and Express!

                Building fullstack applications (i.e. frontend + backend) with the MERN stack is very popular - in this course, you will learn it from scratch at the example of a complete project!
            """.trimIndent(),
            instructor = "Rishi Sunak",
            videoModules = videoModules,
            tags = listOf("Science", "History")
        ),
        Course(
            id = 2,
            title = "The Complete React Native",
            thumbUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerEscapes.jpg",
            shortDescription = "Understand React Native with Hooks, Context, and React Navigation.",
            longDescription = """
                React Native is an excellent solution for developing apps on mobile in a fraction of the time it takes to make an equivalent iOS or Swift app.  You'll love seeing your changes instantly appear on your own device, rather than waiting for Swift/Java code to recompile!  This quick feedback loop, along with excellent cross platform support, is what has catapulted React Native to the top must-have skill for Javascript engineers.
            """.trimIndent(),
            instructor = "Barak Obama",
            videoModules = videoModules,
            tags = listOf("Biology", "Wildlife")
        )
    )

    fun getCourses(): List<Course> {
        // data from: https://gist.github.com/jsturgis/3b19447b304616f18657
        return _courses
    }

    fun getCourse(id: Int): Course? {
        return getCourses().find { id == it.id }
    }

    private val _enrolledCourses = MutableStateFlow<MutableList<Course>>(mutableListOf())
    private val enrolledCourses = _enrolledCourses.asStateFlow()

    fun enroll(course: Course) {
        _enrolledCourses.value += course
    }

    fun disEnroll(course: Course) {
        _enrolledCourses.value.remove(course)
    }

    fun isEnrolled(course: Course): Boolean {
        return _enrolledCourses.value.find { it.id == course.id } != null
    }

    fun getEnrolledCourses(): StateFlow<List<Course>> {
        return enrolledCourses
    }

}

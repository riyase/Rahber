package com.rahbertheadvisor.android.dashboard.model.repository

import com.rahbertheadvisor.android.dashboard.model.Course
import com.rahbertheadvisor.android.dashboard.model.Interest
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
            tags = "Science,History"
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
            tags = "Biology,Wildlife"
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

    val interests = listOf(
        Interest("Agile methods", false),
        Interest("Activism", false),
        Interest("Authenticity", false),
        Interest("GNI", false),
        Interest("Blockchain", false),
        Interest("Blogging", false),
        Interest("Coaching", false),
        Interest("Corporate Social Responsibility", false),
        Interest("Creative thinking", false),
        Interest("Democracy", false),
        Interest("Digital Media", false),
        Interest("Digital Marketing", false),
        Interest("Digitization", false),
        Interest("Diversity", false),
        Interest("Eco-Design", false),
        Interest("Emancipation", false),
        Interest("Energy efficiency", false),
        Interest("Engagement", false),
        Interest("Development", false),
        Interest("Nutrition", false),
        Interest("Europe", false),
        Interest("Fair Finance", false),
        Interest("Family", false),
        Interest("Finance", false),
        Interest("Refugee Solidarity", false),
        Interest("Freedom", false),
        Interest("Peace", false),
        Interest("Science", true),
        Interest("History", false),
        Interest("Biology", false),
        Interest("Wildlife", false),
        Interest("Gastronomy", false),
        Interest("Money system", false),
        Interest("Community", false),
        Interest("Genetic engineering", false),
        Interest("Justice", false),
        Interest("Social commitment", false)
    )

    fun saveInterests(updated: List<Interest>) {
        for (i in interests.indices) {
            if (updated[i].active != interests[i].active) {
                interests[i].active = updated[i].active
            }
        }
    }

    fun getRelatedCourses(): List<Course> {
        val set = hashSetOf<String>()
        enrolledCourses.value.forEach { course ->
            course.tags.split(",").forEach { tag -> set.add(tag) }
        }

        val related = _courses.filter {
            hasTag(it, set)
        }
        return related
    }

    fun getInterestedCourses(): List<Course> {
        return _courses.filter {
            hasTag(it, interests.filter { it.active }.map { it.name }.toSet())
        }
    }

    fun getRecommended(): List<Course> {
        val related = getRelatedCourses()
        val relatedIds = related.map{ it.id }.toHashSet()
        return _courses.filter {
            !relatedIds.contains(it.id)
                    && hasTag(it, interests.filter { it.active }.map { it.name }.toSet())
        }
    }

    private fun hasTag(course: Course, tags: Set<String>): Boolean {
        for (tag in tags) {
            //we can improve the matching by
            if (course.tags.contains(tag)) {
                return true
            }
        }
        return false
    }

}

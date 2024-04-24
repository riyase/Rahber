package com.rahbertheadvisor.android.dashboard.model

data class Course(
    val id: Int,
    val title: String,
    val thumbUrl: String,
    val shortDescription: String,
    val longDescription: String,
    val instructor: String,
    val videoModules: List<VideoModule>,
    val tags: String
)

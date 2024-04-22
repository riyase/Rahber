package com.rahbertheadvisor.android.dashboard.model

import com.rahbertheadvisor.android.dashboard.model.repository.CourseLocalRepository
import com.rahbertheadvisor.android.dashboard.model.repository.CourseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CourseModule {

    @Provides
    fun getRepository(): CourseRepository {
        return CourseLocalRepository()
    }

}
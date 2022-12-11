package com.example.homework_7.di

import android.content.Context
import androidx.room.Room
import com.example.homework_7.data.datasource.TaskDataSource
import com.example.homework_7.data.entity.Task
import com.example.homework_7.data.repo.TaskRepository
import com.example.homework_7.room.MyDatabase
import com.example.homework_7.room.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideTaskRepository(taskDataSource: TaskDataSource): TaskRepository{
        return TaskRepository(taskDataSource)
    }

    @Provides
    @Singleton
    fun provideTaskDataSource(taskDao: TaskDao): TaskDataSource{
        return TaskDataSource(taskDao)
    }

    @Provides
    @Singleton
    fun provideTaskDao(@ApplicationContext context: Context): TaskDao{
        val db = Room.databaseBuilder(context, MyDatabase::class.java, "tasks.sqlite")
            .fallbackToDestructiveMigration().createFromAsset("tasks.sqlite").build()

        return db.getTaskDao()
    }
}
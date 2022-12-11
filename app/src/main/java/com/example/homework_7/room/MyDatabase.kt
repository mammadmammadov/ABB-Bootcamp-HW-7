package com.example.homework_7.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework_7.data.entity.Task

@Database(entities = [Task::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}
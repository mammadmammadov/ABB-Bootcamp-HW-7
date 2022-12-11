package com.example.homework_7.room

import androidx.room.*
import com.example.homework_7.data.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    suspend fun loadTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE name like '%' || :searchText || '%'")
    suspend fun search(searchText: String): List<Task>

    @Insert
    suspend fun save(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}
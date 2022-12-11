package com.example.homework_7.data.repo

import com.example.homework_7.data.datasource.TaskDataSource
import com.example.homework_7.data.entity.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskRepository(var taskDataSource: TaskDataSource) {

    suspend fun save(name: String) = taskDataSource.save(name)

    suspend fun update(id: Int, name: String) = taskDataSource.update(id, name)

    suspend fun delete(id: Int) = taskDataSource.delete(id)

    suspend fun loadTasks(): List<Task>  = taskDataSource.loadTasks()

    suspend fun search(searchText: String): List<Task> = taskDataSource.search(searchText)
}

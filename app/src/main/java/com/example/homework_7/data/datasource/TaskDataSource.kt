package com.example.homework_7.data.datasource

import android.util.Log
import com.example.homework_7.data.entity.Task
import com.example.homework_7.room.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskDataSource(var taskDao: TaskDao) {
    suspend fun save(name: String){
        val newTask = Task(0, name)
        taskDao.save(newTask)
        Log.e("Task Save", name)
    }

    suspend fun update(id: Int, name: String){
        val updateTask = Task(id, name)
        taskDao.update(updateTask)
        Log.e("Task Update", "$id - $name")
    }

    suspend fun delete(id: Int){
        val deleteTask = Task(id, "")
        taskDao.delete(deleteTask)
    }

    suspend fun loadTasks(): List<Task> = withContext(Dispatchers.IO){
        taskDao.loadTasks()
    }

    suspend fun search(searchText: String): List<Task> = withContext(Dispatchers.IO){
        taskDao.search(searchText)
    }
}
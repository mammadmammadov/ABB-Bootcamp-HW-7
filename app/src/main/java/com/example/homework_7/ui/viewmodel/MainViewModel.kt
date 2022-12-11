package com.example.homework_7.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework_7.data.entity.Task
import com.example.homework_7.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var trepo: TaskRepository): ViewModel() {
    var taskList = MutableLiveData<List<Task>>()

    init{
        loadTasks()
    }

    fun delete(id: Int){
        CoroutineScope(Dispatchers.Main).launch {
            trepo.delete(id)
        }

    }

    fun loadTasks(){
        CoroutineScope(Dispatchers.Main).launch {
            taskList.value = trepo.loadTasks()
        }
    }

    fun search(searchText: String){
        CoroutineScope(Dispatchers.Main).launch {
            taskList.value = trepo.search(searchText)
        }
    }


}
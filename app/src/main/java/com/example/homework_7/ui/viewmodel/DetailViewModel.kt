package com.example.homework_7.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.homework_7.R
import com.example.homework_7.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var trepo: TaskRepository): ViewModel() {

    fun update(id: Int, name: String){
        CoroutineScope(Dispatchers.Main).launch {
            trepo.update(id, name)
        }
    }
}
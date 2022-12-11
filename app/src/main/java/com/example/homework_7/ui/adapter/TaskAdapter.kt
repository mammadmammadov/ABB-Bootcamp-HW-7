package com.example.homework_7.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_7.R
import com.example.homework_7.data.entity.Task
import com.example.homework_7.databinding.TaskDesignBinding
import com.example.homework_7.ui.fragments.DetailFragment
import com.example.homework_7.ui.fragments.MainFragmentDirections
import com.example.homework_7.ui.viewmodel.DetailViewModel
import com.example.homework_7.ui.viewmodel.MainViewModel
import com.example.homework_7.util.go
import com.google.android.material.snackbar.Snackbar
import hilt_aggregated_deps._com_example_homework_7_ui_fragments_SaveFragment_GeneratedInjector

class TaskAdapter(var mContext: Context, var tasksList: List<Task>, var viewModel: MainViewModel)
    : RecyclerView.Adapter<TaskAdapter.TaskDesignHolder>() {

    inner class TaskDesignHolder(var binding: TaskDesignBinding): RecyclerView.ViewHolder(binding.root){
//        var binding: TaskDesignBinding
//        init{
//            this.binding = binding
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskDesignHolder {
        val binding:TaskDesignBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.task_design, parent, false)
        return TaskDesignHolder(binding)
    }


    // onBindViewHolder and getItemCount functions work together as it is clear from the code below

    override fun onBindViewHolder(holder: TaskDesignHolder, position: Int) {
        val task = tasksList[position]
        val b = holder.binding //we this value to b for not using each time "holder.binding"
        b.task = task

        b.textViewTaskInfo.text = task.name

        b.taskView.setOnClickListener{
            val transition = MainFragmentDirections.toDetail(task)
            Navigation.go(it, transition)
        }

        b.imageViewDelete.setOnClickListener {
            Snackbar.make(it,"Do you want to delete ${task.name}?",Snackbar.LENGTH_SHORT)
                .setAction("YES"){
                    viewModel.delete(task.id)
                    viewModel.loadTasks()
                }.show()

        }



    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    fun delete(id: Int){
        Log.e("Delete task ", id.toString())
    }

}
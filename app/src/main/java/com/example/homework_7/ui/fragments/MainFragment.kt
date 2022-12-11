package com.example.homework_7.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.homework_7.R
import com.example.homework_7.data.entity.Task
import com.example.homework_7.databinding.ActivityMainBinding
import com.example.homework_7.databinding.FragmentMainBinding
import com.example.homework_7.ui.adapter.TaskAdapter
import com.example.homework_7.ui.viewmodel.DetailViewModel
import com.example.homework_7.ui.viewmodel.MainViewModel
import com.example.homework_7.util.go
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)
        binding.homeFragment = this
        binding.toolbarMainTitle = "TO-DO"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMain) //if we didn't write this line, we
        //couldn't add options menu

//        binding.rv.layoutManager = LinearLayoutManager(requireContext()) no need, cuz we applied MVVM and wrote this in XML

        //binding.rv.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL) - Instagram Story Style

//        var taskList = ArrayList<Task>()
//        val t1 = Task(0,"Homework")
//        val t2 = Task(1, "GYM")
//        val t3 = Task(2,"Android")
//
//        taskList.add(t1)
//        taskList.add(t2)
//        taskList.add(t3)

        viewModel.taskList.observe(viewLifecycleOwner){
            val adapter = TaskAdapter(requireContext(), it, viewModel)
            binding.adapter = adapter
        }

        requireActivity().addMenuProvider(object: MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)

                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@MainFragment)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            } }, viewLifecycleOwner, Lifecycle.State.RESUMED)




        binding.fab.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.toSave)
//            val task = Task(1, "Homework")
//            val transition = MainFragmentDirections.toDetail(task)
//            Navigation.findNavController(it).navigate(transition)

        }

        return binding.root
    }

    fun fabClick(it:View){
        Navigation.go(it, R.id.toSave)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }


    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.search(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.search(newText)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTasks() //IMPORTANT LINE
    }





}
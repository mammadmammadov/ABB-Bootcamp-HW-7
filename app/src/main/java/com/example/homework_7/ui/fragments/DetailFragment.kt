package com.example.homework_7.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.homework_7.R
import com.example.homework_7.databinding.FragmentDetailBinding
import com.example.homework_7.ui.viewmodel.DetailViewModel
import com.example.homework_7.util.go
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false)
        binding.detailFragment = this
        binding.toolbarDetailTitle = "Task Detail"

        val bundle: DetailFragmentArgs by navArgs()
        val resultTask = bundle.task

        binding.task = resultTask


        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun update(id:Int, name: String){
        viewModel.update(id, name)
    }
}
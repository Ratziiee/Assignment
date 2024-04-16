package com.rajat.assignment.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajat.assignment.R
import com.rajat.assignment.databinding.FragmentPostListBinding
import com.rajat.assignment.utils.DataHandler
import com.rajat.assignment.utils.Utils
import com.rajat.assignment.viewmodels.MainViewModel
import com.rajat.assignment.views.adapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostListFragment : Fragment(R.layout.fragment_post_list) {

    private lateinit var  binding : FragmentPostListBinding
    private lateinit var mainViewModel : MainViewModel
    @Inject
    lateinit var postAdapter : PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        Utils.setViewModelCallback(mainViewModel);
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPostListBinding.bind(view)

        init()
        mainViewModel.posts.observe(viewLifecycleOwner) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    postAdapter.differ.submitList(dataHandler.data)
                }
                is DataHandler.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    println("onViewCreated: ERROR " + dataHandler.message)
                }
                is DataHandler.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    println("onViewCreated: LOADING..")

                }
            }
        }

        if(Utils.isInternetConnected(requireContext())) {
            mainViewModel.getPosts()
        } else {
            Toast.makeText(context, "Please connect to internet", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE

        }

    }


    private fun init() {

        postAdapter.onPostClicked {
            val bundle = Bundle().apply {
                putParcelable("post_data",it)
            }

            findNavController().navigate(
                R.id.action_postListFragement_to_detailPostFragment,
                bundle
            )

        }


       binding.recyclerView.apply {
           adapter = postAdapter
           layoutManager = LinearLayoutManager(activity)
       }
    }



}
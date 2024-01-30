package com.giraffe.fakepostsapplication.presentation.allposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.giraffe.fakepostsapplication.databinding.FragmentAllPostsBinding
import com.giraffe.fakepostsapplication.presentation.allposts.adapter.AllPostsAdapter
import com.giraffe.fakepostsapplication.utils.Resource
import com.giraffe.fakepostsapplication.utils.handleApiErrors
import com.giraffe.fakepostsapplication.utils.hide
import com.giraffe.fakepostsapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllPostsFragment : Fragment() {
    private lateinit var binding: FragmentAllPostsBinding
    private val mViewModel by viewModels<AllPostsViewModel>()
    private lateinit var allPostsAdapter: AllPostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allPostsAdapter = AllPostsAdapter {
            val action = AllPostsFragmentDirections.actionAllPostsFragmentToPostDetailsFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAllPosts.adapter = allPostsAdapter
        observeGetAllPosts()
    }

    private fun observeGetAllPosts() {
        lifecycleScope.launch {
            mViewModel.state.collect {
                when (it) {
                    is Resource.Failure -> {
                        binding.loadingBar.hide()
                        handleApiErrors(it) {
                            mViewModel.getAllPosts()
                        }
                    }

                    Resource.Loading -> {
                        binding.loadingBar.show()
                    }

                    is Resource.Success -> {
                        binding.loadingBar.hide()
                        allPostsAdapter.submitList(it.value.posts)
                    }
                }
            }
        }
    }
}
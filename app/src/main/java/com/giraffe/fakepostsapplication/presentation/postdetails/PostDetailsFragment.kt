package com.giraffe.fakepostsapplication.presentation.postdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.giraffe.fakepostsapplication.databinding.FragmentPostDetailsBinding
import com.giraffe.fakepostsapplication.utils.Resource
import com.giraffe.fakepostsapplication.utils.handleApiErrors
import com.giraffe.fakepostsapplication.utils.hide
import com.giraffe.fakepostsapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPostDetailsBinding
    private val mViewModel by viewModels<PostDetailsViewModel>()
    private val args: PostDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.getPostDetails(args.postId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeGetPostDetails()
    }

    private fun observeGetPostDetails() {
        lifecycleScope.launch {
            mViewModel.state.collect {
                when (it) {
                    is Resource.Failure -> {
                        binding.loadingBar.hide()
                        handleApiErrors(it) {
                            mViewModel.getPostDetails(args.postId)
                        }
                    }

                    Resource.Loading -> {
                        binding.loadingBar.show()
                    }

                    is Resource.Success -> {
                        binding.loadingBar.hide()
                        binding.tvTitle.text = it.value.title
                        binding.tvBody.text = it.value.body
                    }
                }
            }
        }
    }
}
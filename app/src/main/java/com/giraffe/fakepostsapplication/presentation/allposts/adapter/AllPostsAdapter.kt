package com.giraffe.fakepostsapplication.presentation.allposts.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.giraffe.fakepostsapplication.databinding.PostRowBinding
import com.giraffe.fakepostsapplication.domain.entity.PostDetailsEntity

class AllPostsAdapter(
    private val onClick: (postId: Int) -> Unit
) : ListAdapter<PostDetailsEntity, AllPostsAdapter.PostVH>(PostsDataDiffUtil()) {

    inner class PostVH(private var binding: PostRowBinding) : ViewHolder(binding.root) {
        fun bind(item: PostDetailsEntity) {
            binding.tvTitle.text = item.title
            binding.tvBody.text = item.body
            binding.root.setOnClickListener {
                onClick(item.id)
            }
        }
    }

    class PostsDataDiffUtil : DiffUtil.ItemCallback<PostDetailsEntity>() {
        override fun areItemsTheSame(
            oldItem: PostDetailsEntity,
            newItem: PostDetailsEntity
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: PostDetailsEntity,
            newItem: PostDetailsEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val binding = PostRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostVH(binding)
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) = holder.bind(getItem(position))
}
package com.moqod.stackoverflowinfo.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moqod.stackoverflowinfo.databinding.RowPostItemBinding
import com.moqod.stackoverflowinfo.entity.Post

class PostsAdapter (diffUtil: DiffUtil.ItemCallback<Post>) : PagedListAdapter<Post, PostsAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(RowPostItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder (private val binding: RowPostItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.item = post
        }

    }
}
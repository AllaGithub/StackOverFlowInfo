package com.moqod.stackoverflowinfo.ui.tags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moqod.stackoverflowinfo.databinding.RowItemBinding
import com.moqod.stackoverflowinfo.entity.Tag
import com.moqod.stackoverflowinfo.listener.GenericListener

class TagsAdapter(private val itemListener: GenericListener<Tag>, diffUtil: DiffUtil.ItemCallback<Tag>) : PagedListAdapter<Tag, TagsAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(RowItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder (private val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tag: Tag) {
            binding.item = tag

            binding.root.setOnClickListener { _ ->
                binding.item?.let {
                    itemListener.onItemSelected(it, adapterPosition)
                }
            }

        }

    }
}
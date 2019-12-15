package com.moqod.stackoverflowinfo.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import com.moqod.stackoverflowinfo.R
import com.moqod.stackoverflowinfo.databinding.PostsFragmentBinding
import com.moqod.stackoverflowinfo.entity.Post
import com.moqod.stackoverflowinfo.network.NetworkStatus
import com.moqod.stackoverflowinfo.ui.base.BaseFragment
import com.moqod.stackoverflowinfo.ui.datasource.TagsDataSource
import com.moqod.stackoverflowinfo.utils.showToastErrorMessage
import kotlinx.android.synthetic.main.posts_fragment.*
import javax.inject.Inject

class PostsFragment : BaseFragment<PostsViewModel>() {


    companion object {
        private val BUNDLE_TAG = this::class.java.simpleName + "_tag"
    }

    @Inject
    lateinit var networkStatus: NetworkStatus

    private lateinit var binding: PostsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PostsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel::class.java)

        binding.vm = vm


        arguments?.let {
            vm?.tag = PostsFragmentArgs.fromBundle(it).tag
        }

        savedInstanceState?.let {
            vm?.tag = savedInstanceState.getString(BUNDLE_TAG)
        }

        vm?.onDataSourceCreated?.observe(viewLifecycleOwner, Observer {

            if (vm?.dataSource != null && vm?.config != null){
                val pagedList: PagedList<Post> = PagedList.Builder(vm?.dataSource!!, vm?.config!!)
                    .setInitialKey(TagsDataSource.START_POSITION)
                    .setNotifyExecutor(mainThreadExecutor)
                    .setFetchExecutor(mainThreadExecutor).build()
                setAdapter(pagedList)
            }
        })


    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(BUNDLE_TAG, vm?.tag)
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()

        if (networkStatus.hasNetworkConnectivity()) {
            vm?.getPosts()
        } else {
            showToastErrorMessage(context, resources.getString(R.string.no_network))
        }
    }

    private fun setAdapter(pagedList: PagedList<Post>) {

        val pagingAdapter = PostsAdapter(object : DiffUtil.ItemCallback<Post>(){

                override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                    return oldItem.questionId == newItem.questionId
                }

                override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                    return oldItem.questionId == newItem.questionId
                }

            })

        pagingAdapter.submitList(pagedList)

        rvPosts.adapter = pagingAdapter

    }

}
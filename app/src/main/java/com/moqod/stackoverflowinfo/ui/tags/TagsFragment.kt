package com.moqod.stackoverflowinfo.ui.tags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import com.moqod.stackoverflowinfo.R
import com.moqod.stackoverflowinfo.databinding.TagsFragmentBinding
import com.moqod.stackoverflowinfo.entity.Tag
import com.moqod.stackoverflowinfo.listener.GenericListener
import com.moqod.stackoverflowinfo.network.NetworkStatus
import com.moqod.stackoverflowinfo.ui.base.BaseFragment
import com.moqod.stackoverflowinfo.ui.datasource.TagsDataSource.Companion.START_POSITION
import com.moqod.stackoverflowinfo.utils.showToastErrorMessage
import kotlinx.android.synthetic.main.tags_fragment.*
import javax.inject.Inject

class TagsFragment : BaseFragment<TagsViewModel>() {

    @Inject
    lateinit var networkStatus: NetworkStatus

    private lateinit var binding: TagsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TagsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm = ViewModelProviders.of(this, viewModelFactory).get(TagsViewModel::class.java)

        binding.vm = vm


        vm?.onDataSourceCreated?.observe(viewLifecycleOwner, Observer {

            if (vm?.dataSource != null && vm?.config != null){
                val pagedList: PagedList<Tag> = PagedList.Builder(vm?.dataSource!!, vm?.config!!)
                    .setInitialKey(START_POSITION)
                    .setNotifyExecutor(mainThreadExecutor)
                    .setFetchExecutor(mainThreadExecutor).build()
                setAdapter(pagedList)
            }
        })

    }

    override fun onResume() {
        super.onResume()

        vm?.setNavigator(this)

        if (networkStatus.hasNetworkConnectivity()) {
            vm?.getTags()
        } else {
            showToastErrorMessage(context, resources.getString(R.string.no_network))
        }

    }

    override fun onPause() {
        super.onPause()
        vm?.removeNavigator(this)
    }


    private fun setAdapter(pagedList: PagedList<Tag>) {

        val pagingAdapter = TagsAdapter(
            object : GenericListener<Tag> {

                override fun onItemSelected(value: Tag, position: Int) {

                    vm?.onItemClick(value.name)

                }
            }, object : DiffUtil.ItemCallback<Tag>(){

                override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
                    return oldItem.name == newItem.name
                }

            })

        pagingAdapter.submitList(pagedList)

        rvTags.adapter = pagingAdapter

    }

}
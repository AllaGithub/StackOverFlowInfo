package com.moqod.stackoverflowinfo.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.moqod.stackoverflowinfo.ui.base.BaseFragment
import com.moqod.stackoverflowinfo.databinding.PostsFragmentBinding

class PostsFragment : BaseFragment<PostsViewModel>() {


    companion object {
        private val BUNDLE_TAG = this::class.java.simpleName + "_tag"
    }


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

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(BUNDLE_TAG, vm?.tag)
        super.onSaveInstanceState(outState)
    }

    //https://api.stackexchange.com/docs/questions#order=desc&sort=activity&tagged=java&filter=default&site=stackoverflow&run=true

}
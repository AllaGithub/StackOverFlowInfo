package com.moqod.stackoverflowinfo.ui.posts

import android.app.Application
import androidx.paging.PagedList
import com.moqod.stackoverflowinfo.BuildConfig
import com.moqod.stackoverflowinfo.entity.Post
import com.moqod.stackoverflowinfo.network.Order
import com.moqod.stackoverflowinfo.network.posts.PostsRespository
import com.moqod.stackoverflowinfo.observable.SingleLiveEvent
import com.moqod.stackoverflowinfo.ui.base.BaseViewModel
import com.moqod.stackoverflowinfo.ui.datasource.PostsDataSource
import com.moqod.stackoverflowinfo.ui.datasource.PostsDataSource.Companion.NUMBER_OF_ITEMS_TO_DOWNLOAD
import javax.inject.Inject

class PostsViewModel @Inject constructor(app: Application): BaseViewModel(app) {


    @Inject
    lateinit var postsRepo: PostsRespository

    var config: PagedList.Config? = null

    var dataSource: PostsDataSource? = null

    val onDataSourceCreated = SingleLiveEvent<Void>()

    var tag: String? = null

    fun getPosts() {
        // DataSource
        tag?.let {
            dataSource = PostsDataSource(getApplication(), it, postsRepo, BuildConfig.SOF_SITE, Post.Sort.ACTIVITY, Order.DESC, schedulerProvider, compositeDisposable)
        }

        // PagedList
        config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(NUMBER_OF_ITEMS_TO_DOWNLOAD)
            .build()

        onDataSourceCreated.call()
    }


}
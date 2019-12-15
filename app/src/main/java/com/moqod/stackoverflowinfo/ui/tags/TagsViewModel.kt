package com.moqod.stackoverflowinfo.ui.tags

import android.app.Application
import androidx.paging.PagedList
import com.moqod.stackoverflowinfo.BuildConfig
import com.moqod.stackoverflowinfo.entity.Tag
import com.moqod.stackoverflowinfo.network.Order
import com.moqod.stackoverflowinfo.network.tags.TagsRepository
import com.moqod.stackoverflowinfo.observable.SingleLiveEvent
import com.moqod.stackoverflowinfo.ui.base.BaseViewModel
import com.moqod.stackoverflowinfo.ui.datasource.TagsDataSource
import com.moqod.stackoverflowinfo.ui.datasource.TagsDataSource.Companion.NUMBER_OF_ITEMS_TO_DOWNLOAD
import javax.inject.Inject

class TagsViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var tagsRepo: TagsRepository

    var config: PagedList.Config? = null

    var dataSource: TagsDataSource? = null

    var onDataSourceCreated = SingleLiveEvent<Void>()

    fun getTags() {
        // DataSource
        dataSource = TagsDataSource(getApplication(), tagsRepo, BuildConfig.SOF_SITE, Tag.Sort.POPULAR, Order.DESC, schedulerProvider, compositeDisposable)

        // PagedList
        config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(NUMBER_OF_ITEMS_TO_DOWNLOAD)
            .build()

        onDataSourceCreated.call()

    }

    fun onItemClick(tagName: String?) {
        tagName?.let {
            val action = TagsFragmentDirections.actionAccountsFragmentToPostsFragment(it)
            appNavigator?.get()?.navigateTo(action)

        }
    }

}
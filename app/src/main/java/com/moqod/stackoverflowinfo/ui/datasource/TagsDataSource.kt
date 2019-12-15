package com.moqod.stackoverflowinfo.ui.datasource

import android.annotation.SuppressLint
import android.content.Context
import androidx.paging.PositionalDataSource
import com.moqod.stackoverflowinfo.entity.Tag
import com.moqod.stackoverflowinfo.network.Order
import com.moqod.stackoverflowinfo.network.tags.TagsRepository
import com.moqod.stackoverflowinfo.rx.SchedulerProvider
import com.moqod.stackoverflowinfo.utils.showToastErrorMessage
import io.reactivex.disposables.CompositeDisposable

class TagsDataSource (private val context: Context,
                      private val repo: TagsRepository,
                      private val site: String,
                      private val sort: Tag.Sort,
                      private val order: Order,
                      private val schedulerProvider: SchedulerProvider,
                      private val compDis: CompositeDisposable) : PositionalDataSource<Tag>() {

    companion object {
        const val START_POSITION: Int = 1
        const val NUMBER_OF_ITEMS_TO_DOWNLOAD: Int = 20
    }

    @SuppressLint("WrongThread")
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Tag>) {


        repo.getTags(site, sort, order, START_POSITION, params.requestedLoadSize)
            .compose(schedulerProvider.ioToMainSingleScheduler())
            .subscribe { it, t ->


                if (t != null) {
                    showToastErrorMessage(context, it.message())
                    return@subscribe
                }

                if (it.isSuccessful) {
                    it.body()?.items?.let { it1 ->
                        callback.onResult(it1, 0) }
                } else {
                    showToastErrorMessage(context, it.message())
                }
            }.apply {
                compDis.add(this)
            }

    }

    @SuppressLint("WrongThread")
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Tag>) {

        repo.getTags(site, sort, order, params.startPosition, params.loadSize)
            .compose(schedulerProvider.ioToMainSingleScheduler())
            .subscribe { it, t ->


                if (t != null) {
                    showToastErrorMessage(context, it.message())
                    return@subscribe
                }

                if (it.isSuccessful) {
                    it.body()?.items?.let { it1 -> callback.onResult(it1) }
                } else {
                    showToastErrorMessage(context, it.message())
                }
            }.apply {
                compDis.add(this)
            }
    }


}
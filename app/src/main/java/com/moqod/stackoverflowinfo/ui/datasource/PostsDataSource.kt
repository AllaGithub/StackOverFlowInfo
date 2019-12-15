package com.moqod.stackoverflowinfo.ui.datasource

import android.annotation.SuppressLint
import android.content.Context
import androidx.paging.PositionalDataSource
import com.moqod.stackoverflowinfo.entity.Post
import com.moqod.stackoverflowinfo.network.Order
import com.moqod.stackoverflowinfo.network.posts.PostsRespository
import com.moqod.stackoverflowinfo.rx.SchedulerProvider
import com.moqod.stackoverflowinfo.utils.showToastErrorMessage
import io.reactivex.disposables.CompositeDisposable

class PostsDataSource (private val context: Context,
                       private val tag: String,
                       private val repo: PostsRespository,
                       private val site: String,
                       private val sort: Post.Sort,
                       private val order: Order,
                       private val schedulerProvider: SchedulerProvider,
                       private val compDis: CompositeDisposable
) : PositionalDataSource<Post>() {

    companion object {
        const val START_POSITION: Int = 1
        const val NUMBER_OF_ITEMS_TO_DOWNLOAD: Int = 20
    }

    @SuppressLint("WrongThread")
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Post>) {


        repo.getPosts(site, sort, order, tag, START_POSITION, params.requestedLoadSize)
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
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Post>) {

        repo.getPosts(site, sort, order, tag, params.startPosition, params.loadSize)
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
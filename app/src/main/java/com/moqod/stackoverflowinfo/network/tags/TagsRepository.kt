package com.moqod.stackoverflowinfo.network.tags

import com.moqod.stackoverflowinfo.entity.Tag
import com.moqod.stackoverflowinfo.entity.TagResponse
import com.moqod.stackoverflowinfo.network.Order
import io.reactivex.Single
import retrofit2.Response

interface TagsRepository {

    fun getTags(site: String, sort: Tag.Sort, order: Order, page: Int, pageSize: Int): Single<Response<TagResponse>>

}
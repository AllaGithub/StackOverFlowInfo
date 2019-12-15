package com.moqod.stackoverflowinfo.network.tags

import com.moqod.stackoverflowinfo.BuildConfig
import com.moqod.stackoverflowinfo.entity.Tag
import com.moqod.stackoverflowinfo.entity.TagResponse
import com.moqod.stackoverflowinfo.network.Order
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TagsService {

    @GET(BuildConfig.API_VERSION + "/tags")
    fun getTags(
        @Query("site") site: String,
        @Query("sort") sort: Tag.Sort,
        @Query("order") order: Order,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int): Single<Response<TagResponse>>

}
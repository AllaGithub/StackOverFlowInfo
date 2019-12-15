package com.moqod.stackoverflowinfo.network.posts

import com.moqod.stackoverflowinfo.BuildConfig
import com.moqod.stackoverflowinfo.entity.Post
import com.moqod.stackoverflowinfo.entity.PostResponse
import com.moqod.stackoverflowinfo.network.Order
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsService {

    @GET(BuildConfig.API_VERSION + "/questions")
    fun getTags(
        @Query("site") site: String,
        @Query("sort") sort: Post.Sort,
        @Query("order") order: Order,
        @Query("tagges") tagged: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int): Single<Response<PostResponse>>
}
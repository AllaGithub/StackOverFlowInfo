package com.moqod.stackoverflowinfo.network.posts

import com.moqod.stackoverflowinfo.entity.Post
import com.moqod.stackoverflowinfo.entity.PostResponse
import com.moqod.stackoverflowinfo.network.Order
import io.reactivex.Single
import retrofit2.Response

interface PostsRespository {

    fun getPosts(site: String, sort: Post.Sort, order: Order, tag: String, page: Int, pageSize: Int) : Single<Response<PostResponse>>
}
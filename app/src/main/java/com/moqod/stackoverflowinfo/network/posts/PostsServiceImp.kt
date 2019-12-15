package com.moqod.stackoverflowinfo.network.posts

import com.moqod.stackoverflowinfo.entity.Post
import com.moqod.stackoverflowinfo.entity.PostResponse
import com.moqod.stackoverflowinfo.network.Order
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class PostsServiceImp @Inject constructor(private val postService: PostsService) : PostsRespository {

    override fun getPosts(
        site: String,
        sort: Post.Sort,
        order: Order,
        tag: String,
        page: Int,
        pageSize: Int
    ): Single<Response<PostResponse>> {
        return postService.getTags(site, sort, order, tag, page, pageSize)
    }
}
package com.moqod.stackoverflowinfo.ui.posts

import android.app.Application
import com.moqod.stackoverflowinfo.ui.base.BaseViewModel
import javax.inject.Inject

class PostsViewModel @Inject constructor(app: Application): BaseViewModel(app) {

    var tag: String? = null


}
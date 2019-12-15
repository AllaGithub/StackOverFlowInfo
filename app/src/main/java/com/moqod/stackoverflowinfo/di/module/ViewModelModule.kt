package com.moqod.stackoverflowinfo.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moqod.stackoverflowinfo.annotation.ViewModelKey
import com.moqod.stackoverflowinfo.di.factory.ViewModelFactory
import com.moqod.stackoverflowinfo.ui.posts.PostsViewModel
import com.moqod.stackoverflowinfo.ui.tags.TagsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(TagsViewModel::class)
    internal abstract fun listViewModel(viewModel: TagsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    internal abstract fun detailsViewModel(viewModel: PostsViewModel): ViewModel


}

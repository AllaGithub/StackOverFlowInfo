package com.moqod.stackoverflowinfo.di.module

import com.moqod.stackoverflowinfo.di.scope.FragmentScoped
import com.moqod.stackoverflowinfo.ui.posts.PostsFragment
import com.moqod.stackoverflowinfo.ui.tags.TagsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @FragmentScoped
    @ContributesAndroidInjector()
    internal abstract fun provideAccountsFragment(): TagsFragment

    @FragmentScoped
    @ContributesAndroidInjector()
    internal abstract fun provideDetailsFragment(): PostsFragment
}
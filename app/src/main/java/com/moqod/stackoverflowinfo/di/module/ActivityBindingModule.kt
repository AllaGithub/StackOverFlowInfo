package com.moqod.stackoverflowinfo.di.module


import com.moqod.stackoverflowinfo.MainActivity
import com.moqod.stackoverflowinfo.di.scope.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity
}
package com.moqod.stackoverflowinfo


import com.moqod.sof.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class SofApp : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}
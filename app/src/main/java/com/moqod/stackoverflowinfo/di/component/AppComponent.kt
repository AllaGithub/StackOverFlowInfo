package com.moqod.sof.di.component

import android.app.Application
import com.moqod.stackoverflowinfo.di.module.ActivityBindingModule
import com.moqod.stackoverflowinfo.di.module.AppModule
import com.moqod.stackoverflowinfo.di.module.NetworkModule
import com.moqod.stackoverflowinfo.di.module.ViewModelModule
import com.moqod.stackoverflowinfo.SofApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewModelModule::class,
    NetworkModule::class,
    ActivityBindingModule::class
])
interface AppComponent : AndroidInjector<SofApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: SofApp?)
}
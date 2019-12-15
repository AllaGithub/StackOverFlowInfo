package com.moqod.stackoverflowinfo.di.module

import android.app.Application
import android.content.Context
import android.os.Handler
import com.moqod.stackoverflowinfo.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

    @Provides
    internal fun provideHandler(): Handler = Handler()

}
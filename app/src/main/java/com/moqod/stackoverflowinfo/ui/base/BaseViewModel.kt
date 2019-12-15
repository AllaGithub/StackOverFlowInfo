package com.moqod.stackoverflowinfo.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moqod.stackoverflowinfo.AppNavigator
import com.moqod.stackoverflowinfo.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference
import javax.inject.Inject

open class BaseViewModel @Inject constructor(application: Application) : AndroidViewModel(application), BaseViewModelInterface {

    var appNavigator: WeakReference<AppNavigator>? = null

    final override val showGlobalSpinner: MutableLiveData<Boolean> = MutableLiveData()

    @Inject
    lateinit var compositeDisposable: CompositeDisposable
    @Inject
    lateinit var schedulerProvider: SchedulerProvider


    init {
        showGlobalSpinner.value = false
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    override fun getViewModel(): ViewModel? {
        return this
    }

    override fun setNavigator(navigator: AppNavigator) {
        appNavigator = WeakReference(navigator)
    }

    override fun removeNavigator(navigator: AppNavigator) {
        if (appNavigator == navigator) {
            appNavigator = null
        }
    }
}
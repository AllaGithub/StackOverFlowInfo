package com.moqod.stackoverflowinfo.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moqod.stackoverflowinfo.AppNavigator

interface BaseViewModelInterface {

    fun getViewModel(): ViewModel?

    fun setNavigator(navigator: AppNavigator)
    fun removeNavigator(navigator: AppNavigator)

    val showGlobalSpinner: MutableLiveData<Boolean>
}
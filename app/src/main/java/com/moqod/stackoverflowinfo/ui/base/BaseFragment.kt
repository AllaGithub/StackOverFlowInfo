package com.moqod.stackoverflowinfo.ui.base

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.moqod.stackoverflowinfo.AppNavigator
import com.moqod.stackoverflowinfo.MainActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T : ViewModel> : DaggerFragment(), AppNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    protected var vm: T? = null
        set(value) {
            field = value

            field?.let { model ->
                if (model is BaseViewModelInterface) {
                    model.showGlobalSpinner.observe(viewLifecycleOwner, Observer {
                        if (it) {
                            showSpinner()
                        } else {
                            hideSpinner()
                        }
                    })
                }
            }
        }


    override fun navigateTo(destination: NavDirections) {
        if (findNavController().currentDestination?.getAction(destination.actionId) != null) {
            this.findNavController().navigate(destination)
        }
    }


    fun hideSpinner() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideSpinner()
        }
    }

    fun showSpinner() {
        if (activity is MainActivity) {
            (activity as MainActivity).showSpinner()
        }
    }
}
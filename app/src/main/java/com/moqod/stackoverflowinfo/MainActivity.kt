package com.moqod.stackoverflowinfo

import android.os.Bundle
import com.moqod.stackoverflowinfo.view.ProgressDialogFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private val tagSpinner = "SPINNER_DIALOG"

    private var spinner: ProgressDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    fun hideSpinner() = spinner?.dismiss()

    fun showSpinner() {
        if (spinner != null) {
            spinner?.dismiss()
            spinner = null
        }

        spinner = ProgressDialogFragment.newInstance()

        spinner?.show(supportFragmentManager, tagSpinner)
    }
}

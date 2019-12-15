package com.moqod.stackoverflowinfo.utils

import android.content.Context
import android.widget.Toast
import com.moqod.stackoverflowinfo.R

fun showToastErrorMessage(context: Context?, msg: String?) {
    Toast.makeText(context, msg ?: context?.getText(R.string.general_error), Toast.LENGTH_LONG).show()
}


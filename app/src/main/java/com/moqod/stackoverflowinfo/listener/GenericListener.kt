package com.moqod.stackoverflowinfo.listener

import androidx.recyclerview.widget.RecyclerView

interface GenericListener<T> {

    fun onItemSelected(value: T, position: Int)
}
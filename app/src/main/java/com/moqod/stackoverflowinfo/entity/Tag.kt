package com.moqod.stackoverflowinfo.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tag () : BaseObservable() {

    enum class Sort {
        POPULAR, NAME
    }

    @SerializedName("count")
    @Expose
    var count: String? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.count)
        }

    @SerializedName("name")
    @Expose
    var name: String? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

}
package com.moqod.stackoverflowinfo.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Post : BaseObservable() {

    enum class Sort {
        ACTIVITY, VOTES, CREATION, HOT, WEEK, MONTH
    }

    @SerializedName("title")
    @Expose
    var title: String? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @SerializedName("creation_date")
    @Expose
    var postDate: Long? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.postDate)
        }

    @SerializedName("owner")
    @Expose
    var owner: Owner? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.owner)
        }
}
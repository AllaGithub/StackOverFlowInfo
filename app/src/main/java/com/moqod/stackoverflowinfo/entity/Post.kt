package com.moqod.stackoverflowinfo.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Post : BaseObservable() {

    enum class Sort {
        ACTIVITY {
            override fun toString(): String {
                return "activity"
            }
        },
        VOTES {
            override fun toString(): String {
                return "votes"
            }
        },
        CREATION {
            override fun toString(): String {
                return "creation"
            }
        },
        HOT {
            override fun toString(): String {
                return "hot"
            }
        }
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

    @SerializedName("last_activity_date")
    @Expose
    var lastActivityDate: Long? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.lastActivityDate)
        }

    @SerializedName("question_id")
    @Expose
    var questionId: Long? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.questionId)
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
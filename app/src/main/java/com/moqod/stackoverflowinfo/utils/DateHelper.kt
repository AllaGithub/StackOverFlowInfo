package com.moqod.stackoverflowinfo.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    @JvmStatic
    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        return format.format(date)
    }
}
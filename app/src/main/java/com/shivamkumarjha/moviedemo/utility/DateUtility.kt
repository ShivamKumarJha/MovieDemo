package com.shivamkumarjha.moviedemo.utility


import android.text.format.DateUtils
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtility {

    fun getRelativeTime(text: String): String? {
        val dateFormat = "yyyy-MM-dd"
        val format: DateFormat = SimpleDateFormat(dateFormat, Locale.ROOT)
        try {
            val date = format.parse(text) ?: return null
            return DateUtils.getRelativeTimeSpanString(
                date.time,
                System.currentTimeMillis(),
                DateUtils.SECOND_IN_MILLIS
            ).toString()
        } catch (exception: Exception) {
            return null
        }
    }

}
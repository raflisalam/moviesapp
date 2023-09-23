package com.raflisalam.moviesapp.core.common.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

object TimeUtils {
    fun formatRuntimeToHoursMinutes(totalTimeMovies: Int): String {
        val hours = totalTimeMovies / 60
        val minutes = totalTimeMovies % 60
        return String.format("%dh %dm", hours, minutes)
    }

    fun formatDate(inputDate: String): String {
        if (inputDate.isEmpty()) {
            return ""
        }
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = inputFormat.parse(inputDate)

            if (date != null) {
                val formattedDate = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault()).format(date)
                formattedDate
            } else {
                "Tanggal tidak valid"
            }
        } catch (e: ParseException) {
            "Tanggal tidak valid"
        }
    }

}
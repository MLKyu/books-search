package com.alansoft.kapaycote.utils

import android.text.TextUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by LEE MIN KYU on 2021/04/27
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
object DateTimeUtils {
    @JvmStatic
    fun parseDate(date: Date?): String {
        var date = date
        if (date == null) {
            date = Date()
        }
        val outputFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
        return outputFormat.format(date)
    }

    @JvmStatic
    fun fromDateString(dateString: String): Date? {
        return if (TextUtils.isEmpty(dateString)) {
            null
        } else try {
            val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.KOREA)
            df.parse(dateString)
        } catch (e: ParseException) {
            null
        } catch (nfe: NumberFormatException) {
            null
        }
    }

    @JvmStatic
    fun toDateString(date: Date?): String? {
        if (date == null) {
            return null
        }
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.KOREA)
        return df.format(date)
    }
}
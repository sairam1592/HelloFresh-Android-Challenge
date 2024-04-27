package com.hellofresh.task2.common

import com.hellofresh.task2.common.AppConstants.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentData(): String =
    SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date())

package com.codercampy.gmailtest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mail(
    val timestamp: Long,
    val from: String,
    val subject: String,
    val body: String, //html
) : Parcelable
package com.codercampy.gmailtest

import android.graphics.Color
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.random.Random

fun parseTime(time: Long): String {
    val sdf = SimpleDateFormat("dd MMM, h:mm a", Locale.getDefault())
    return sdf.format(time)
}

val randomColors = arrayOf(
    "#B71C1C", "#880E4F", "#4A148C", "#311B92", "#1A237E", "#0D47A1"
)

fun getRandomColor(): Int {
    return Color.parseColor(randomColors[Random.nextInt(0, randomColors.size)])
}

val alphabets = "qwertyuiopasdfghjklzxcvbnm"
fun getRandomAlphabets(): String {
    return alphabets[Random.nextInt(0, alphabets.length)].toString()
}
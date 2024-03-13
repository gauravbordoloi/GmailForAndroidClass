package com.codercampy.gmailtest

val froms = listOf<String>(
    "gaurav@homedrop.in",
)

val subject = listOf<String>(
    "Randome sub",
)

val bodies = listOf<String>(
    "Randome sub",
)

fun getMails(): List<Mail> {
    val mails = mutableListOf<Mail>()
    for (i in 0 until 100) {
        mails.add(
            Mail(
                System.currentTimeMillis(),
                froms[i],
                subject[i],
                bodies[i]
            )
        )
    }
    mails.sortByDescending { it.timestamp }
    return mails
}
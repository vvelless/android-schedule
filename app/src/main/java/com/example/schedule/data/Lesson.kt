package com.example.schedule.data

import java.util.Date

data class Lesson(
    val name: String,
    val professor: String,
    val classroom: String,
    val startTime: Date,
    val endTime: Date
)
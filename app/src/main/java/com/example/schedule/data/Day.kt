package com.example.schedule.data

import java.time.DayOfWeek

data class Day(
    val weekDay: WeekDay,
    val startWith: Int,
    val lessons: List<Lesson>

)

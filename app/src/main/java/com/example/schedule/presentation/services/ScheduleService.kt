package com.example.schedule.presentation.services

import com.example.schedule.data.Day
import com.example.schedule.data.Lesson
import com.example.schedule.data.Schedule
import com.example.schedule.data.Week
import com.example.schedule.data.WeekDay
import java.util.Calendar
import java.util.Date

object ScheduleService {
    private val schedule = Schedule(
        studyingGroupName = "ПрИ-302",
        inFirstWeek = Week(
            days = listOf(
                Day(
                    WeekDay.TUESDAY,
                    1,
                    listOf(
                        Lesson(
                            name = "Экономика программной инженерии",
                            professor = "Ткач",
                            classroom = "221",
                            startTime = Date().apply {
                                hours = 15
                                minutes = 0
                            },
                            endTime = Date().apply {
                                hours = 16
                                minutes = 30
                            }
                        ),
                        Lesson(
                            name = "Базы и хранилища данных",
                            professor = "Барабан",
                            classroom = "413",
                            startTime = Date().apply {
                                hours = 18
                                minutes = 20
                            },
                            endTime = Date().apply {
                                hours = 19
                                minutes = 50
                            }
                        ),
                        Lesson(
                            name = "Базы и хранилища данных",
                            professor = "Барабан",
                            classroom = "413",
                            startTime = Date().apply {
                                hours = 19
                                minutes = 55
                            },
                            endTime = Date().apply {
                                hours = 21
                                minutes = 25
                            }
                        )
                    )
                )
            )
        ),
        inSecondWeek = Week(
            days = listOf(
                Day(
                    WeekDay.TUESDAY,
                    1,
                    listOf(
                        Lesson(
                            name = "Экономика программной инженерии",
                            professor = "Ткач",
                            classroom = "221",
                            startTime = Date().apply {
                                hours = 15
                                minutes = 0
                            },
                            endTime = Date().apply {
                                hours = 16
                                minutes = 30
                            }
                        ),
                        Lesson(
                            name = "Экономика программной инженерии",
                            professor = "Ткач",
                            classroom = "221",
                            startTime = Date().apply {
                                hours = 15
                                minutes = 0
                            },
                            endTime = Date().apply {
                                hours = 16
                                minutes = 30
                            }
                        )
                    )
                )
            )
        )
    )

    fun getWeekByNumber(number: Int): Week {
        if (number != 1 && number != 2) {
            throw RuntimeException("Wrong week number")
        }
        return if (number == 1) {
            schedule.inFirstWeek
        } else {
            schedule.inSecondWeek
        }
    }

    fun getDayByDayOfWeek(weekDay: WeekDay, weekNumber: Int): Day {
        val week = getWeekByNumber(weekNumber)
        return week.days.firstOrNull { it.weekDay == weekDay }
            ?: throw RuntimeException("Day of week ${weekDay.name} in week number $weekNumber does not exist")
    }

    fun getDayNow(): Day {
        val today = Date().day
        val dayNumber = if (today == 0) 6 else today - 1
        val dayOfWeek = WeekDay.getByNumber(dayNumber)
        return runCatching { getDayByDayOfWeek(dayOfWeek, getNowWeekNumber()) }
            .getOrDefault(createEmptyDay(dayOfWeek))
    }

    fun getWeekNow(): Week {
        return if (getNowWeekNumber() == 1) {
            schedule.inFirstWeek
        } else {
            schedule.inSecondWeek
        }
    }

    fun getNowWeekNumber(): Int {
        val currentDate = Date()
        val calendar = Calendar.getInstance().apply { time = currentDate }
        val currentWeek = calendar.get(Calendar.WEEK_OF_YEAR)

        val firstSeptember = Calendar.getInstance().apply {
            set(Calendar.MONTH, Calendar.SEPTEMBER)
            set(Calendar.DAY_OF_MONTH, 1)
        }
        val startWeek = firstSeptember.get(Calendar.WEEK_OF_YEAR)

        return (currentWeek - startWeek) % 2 + 1
    }

    private fun createEmptyDay(weekDay: WeekDay): Day = Day(weekDay, 0, listOf())
}



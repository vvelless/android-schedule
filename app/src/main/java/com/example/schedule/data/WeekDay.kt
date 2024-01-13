package com.example.schedule.data

enum class WeekDay (val value: String){
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    companion object {
        private val days = WeekDay.values()
        fun getByNumber(number: Int): WeekDay {
            if (number < 0 || number >= days.size) {
                throw RuntimeException("Wrong day number $number")
            }
            return days[number]
        }
    }

}
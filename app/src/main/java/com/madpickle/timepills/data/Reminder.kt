package com.madpickle.timepills.data

import java.util.*


data class Reminder(
    val id: Int,
    var title: String = "",
    var description: String = "",
    var type: TypeMedicine,
    var infinity: Boolean = false,
    var leftReminders: Int = 0,             //количество оставшихся напоминаний
    val dates: List<Calendar>
)
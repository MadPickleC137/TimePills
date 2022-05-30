package com.madpickle.timepills.data

data class User(
    var id: String,
    var reminders: List<Reminder>,
    var langCode: String = "RU",
    var messageSong: String,
    var periodReminder: PeriodReminder
)
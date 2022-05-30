package com.madpickle.timepills.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.madpickle.timepills.core.AppPreferences
import com.madpickle.timepills.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(private val pref: AppPreferences) : ViewModel() {
    val showIntroFragment = MutableLiveData<Event<Boolean>>()

    init {
        showIntroFragment.value = Event(pref.isFirstRun())
    }

    fun introIsShowed() {
        pref.setIsFirstRun()
    }
}

package com.madpickle.timepills.main

import android.content.Context
import com.madpickle.timepills.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @ActivityScoped
    @Provides
    fun provideTestString( @ApplicationContext context: Context) = context.getString(R.string.app_name)

}
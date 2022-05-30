package com.madpickle.timepills.utils

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Animation
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import timber.log.Timber
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


fun Int.toPx(context: Context) = (this * context.resources.displayMetrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT

fun Context.getColorRes(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)
fun Context.ActiveUiMode() =  this.resources.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)

fun NavController.safeNavigate(
    @IdRes currentDestinationId: Int,
    @IdRes id: Int,
    args: Bundle? = null
) {
    if (currentDestinationId == currentDestination?.id) {
        navigate(id, args)
    }
}

fun NavController.safeNavigate(direction: Int) {
    Timber.d("navigation click")
    currentDestination?.getAction(direction)?.run {
        navigate(direction)
    }
}

fun View.slideUp() {
    this.visibility = View.VISIBLE
    val animate = TranslateAnimation(
        0F,  // fromXDelta
        0F,  // toXDelta
        this.height.toFloat(),  // fromYDelta
        0F) // toYDelta
    animate.duration = 350
    animate.fillAfter = true
    this.startAnimation(animate)
}

fun View.slideDown() {
    val view = this
    val animate = TranslateAnimation(
        0F,  // fromXDelta
        0F,  // toXDelta
        0F,  // fromYDelta
        this.height.toFloat()) // toYDelta
    animate.duration = 350
    animate.fillAfter = true
    this.startAnimation(animate)
    animate.setAnimationListener(object : Animation.AnimationListener{
        override fun onAnimationStart(animation: Animation?) {}
        override fun onAnimationEnd(animation: Animation?) { view.visibility = View.GONE }
        override fun onAnimationRepeat(animation: Animation?) {}
    })
}

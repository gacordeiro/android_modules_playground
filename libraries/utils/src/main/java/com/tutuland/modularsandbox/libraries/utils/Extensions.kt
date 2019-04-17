package com.tutuland.modularsandbox.libraries.utils

import android.view.View
import android.view.animation.Animation

const val DURATION_VERY_SHORT: Long = 250L //Quick debounce for actions
const val DURATION_SHORT: Long = 500L //For actions that can happen twice in the same screen
const val DURATION_MEDIUM: Long = 800L //Long debounce for actions
const val DURATION_LONG: Long = 1500L //For actions that trigger screen transitions or are blocking
const val DURATION_VERY_LONG: Long = 3000L //For actions that invoke long requests

fun View?.show(animation: Animation? = null, duration: Long = DURATION_VERY_SHORT): View? =
    this?.apply {
        if (visibility != View.VISIBLE) {
            visibility = View.VISIBLE
            if (animation != null) {
                animation.duration = duration
                startAnimation(animation)
            }
        }
    }

fun View?.hide(animation: Animation? = null, duration: Long = DURATION_VERY_SHORT): View? =
    this?.apply {
        if (visibility != View.INVISIBLE) {
            visibility = View.INVISIBLE
            if (animation != null) {
                animation.duration = duration
                startAnimation(animation)
            }
        }
    }

fun View?.gone(animation: Animation? = null, duration: Long = DURATION_VERY_SHORT): View? =
    this?.apply {
        if (visibility != View.GONE) {
            visibility = View.GONE
            if (animation != null) {
                animation.duration = duration
                startAnimation(animation)
            }
        }
    }
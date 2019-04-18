package com.tutuland.modularsandbox.libraries.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.Subject

const val DURATION_VERY_SHORT: Long = 250L //Quick debounce for actions
const val DURATION_SHORT: Long = 500L //For actions that can happen twice in the same screen
const val DURATION_MEDIUM: Long = 800L //Long debounce for actions
const val DURATION_LONG: Long = 1500L //For actions that trigger screen transitions or are blocking
const val DURATION_VERY_LONG: Long = 3000L //For actions that invoke long requests

fun AppCompatActivity.setToolbar(
    toolbar: Toolbar?, showHomeAsUp: Boolean = true,
    @DrawableRes upIndicator: Int = R.drawable.ic_back, title: String = ""
) {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        setDisplayHomeAsUpEnabled(showHomeAsUp)
        setHomeAsUpIndicator(upIndicator)
        if (title.isNotEmpty()) this.title = title
    }
}

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

fun Context?.inflate(
    layoutId: Int, viewGroup: ViewGroup? = null, attachToRoot: Boolean = false
): View = this?.let {
    LayoutInflater.from(this).inflate(layoutId, viewGroup, attachToRoot)
} ?: throw IllegalStateException("Cannot inflate layout from a null view group")

fun ViewGroup?.inflate(layoutId: Int, attachToRoot: Boolean = false): View = this?.let {
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
} ?: throw IllegalStateException("Cannot inflate layout from a null view group")


fun CharSequence?.isNotNullOrEmpty(): Boolean = !isNullOrEmpty()

fun <T> Collection<T>?.isNotNullOrEmpty(): Boolean = !orEmpty().isEmpty()


val ioScheduler: Scheduler get() = Schedulers.io()
val uiScheduler: Scheduler get() = AndroidSchedulers.mainThread()

infix fun <T> Subject<T>?.send(emission: T) = this?.onNext(emission)

operator fun CompositeDisposable?.minusAssign(d: Disposable) {
    this?.remove(d)
}

operator fun CompositeDisposable?.plusAssign(d: Disposable) {
    this?.add(d)
}
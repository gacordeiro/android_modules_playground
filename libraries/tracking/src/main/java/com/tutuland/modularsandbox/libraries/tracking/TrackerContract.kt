package com.tutuland.modularsandbox.libraries.tracking

import timber.log.Timber

interface Tracker {
    infix fun track(event: Event)

    sealed class Event(val eventType: String) {
        data class ScreenView(
            val screenName: String
        ) : Event("screen_view")

        data class UserInteraction(
            val category: String,
            val action: String,
            val label: String = "",
            val value: Int = 0,
            val noInteraction: Boolean = false
        ) : Event("user_interaction")
    }
}

class AppTracker(private val trackerList: List<Tracker>) : Tracker {
    override fun track(event: Tracker.Event) = trackerList.forEach { it track event }
}

class TimberTracker : Tracker {
    override fun track(event: Tracker.Event) {
        Timber.d("---------------------------------------")
        Timber.d("track: ${event.eventType}")
        when (event) {
            is Tracker.Event.ScreenView -> event.track()
            is Tracker.Event.UserInteraction -> event.track()
        }
        Timber.d("---------------------------------------")
    }

    private fun Tracker.Event.ScreenView.track() {
        Timber.d("screenName: $screenName")
    }

    private fun Tracker.Event.UserInteraction.track() {
        Timber.d("category: $category")
        Timber.d("action: $action")
        Timber.d("label: $label")
        Timber.d("value: $value")
        Timber.d("noInteraction: $noInteraction")
    }
}
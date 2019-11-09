package com.jasonjerome.pulsarapatientsync.util

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
class Event<T>(private val mContent: T?) {

    private var hasBeenHandled = false

    val contentIfNotHandled: T?
        get() {
            if (hasBeenHandled) {
                return null
            } else {
                hasBeenHandled = true
                return mContent
            }
        }

    init {
        if (mContent == null) {
            throw IllegalArgumentException("null values in Event are not allowed.")
        }
    }

    fun hasBeenHandled(): Boolean {
        return hasBeenHandled
    }
}
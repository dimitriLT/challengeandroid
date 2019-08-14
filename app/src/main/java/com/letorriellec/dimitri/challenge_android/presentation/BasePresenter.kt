package com.letorriellec.dimitri.challenge_android.presentation

interface BasePresenter<in V> {
    fun onViewDetach()
    fun onViewAttached(view: V)
}
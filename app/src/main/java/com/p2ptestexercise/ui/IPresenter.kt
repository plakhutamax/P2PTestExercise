package com.p2ptestexercise.ui

import androidx.lifecycle.LifecycleOwner

interface IPresenter<View: IView, Router: IRouter> {
    val view: View
    val router: Router

    fun bind(view: View, router: Router, lifecycleOwner: LifecycleOwner)
}
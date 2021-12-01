package com.p2ptestexercise.ui

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment<View : IView, Router : IRouter, Presenter : IPresenter<View, Router>> :
    Fragment(), IView, IRouter {

    abstract val presenter: Presenter

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        presenter.bind(this as View, this as Router, viewLifecycleOwner)
    }
}
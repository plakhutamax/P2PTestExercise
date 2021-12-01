package com.p2ptestexercise.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.p2ptestexercise.MainRouter

abstract class BaseFragment<View : IView, Router : IRouter, Presenter : IPresenter<View, Router>> :
    Fragment(), IView, IRouter {

    abstract val presenter: Presenter

    lateinit var mainRouter: MainRouter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Better to name as `routeHost` and allow attaching to activity or fragment
        require(context is MainRouter) {
            "Fragments should be attached to router"
        }
        mainRouter = context

    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        presenter.bind(this as View, this as Router, viewLifecycleOwner)
    }
}
package com.p2ptestexercise.ui

import androidx.annotation.CallSuper
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.*

abstract class BasePresenter<View : IView, Router : IRouter> :
    IPresenter<View, Router>,
    DefaultLifecycleObserver,
    CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext = job + Dispatchers.Main

    private var _view: View? = null
    override val view: View
        get() = requireNotNull(_view) {
            "Presenter should be attached to view before used"
        }

    private var _router: Router? = null
    override val router: Router
        get() = requireNotNull(_router) {
            "Presenter should be attached to router before used"
        }

    private var lifecycleOwner: LifecycleOwner? = null

    @CallSuper
    override fun bind(view: View, router: Router, lifecycleOwner: LifecycleOwner) {
        _view = view
        _router = router
        this.lifecycleOwner = lifecycleOwner
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        cancel()
        lifecycleOwner?.lifecycle?.removeObserver(this)
        lifecycleOwner = null
        _view = null
        _router = null
    }
}
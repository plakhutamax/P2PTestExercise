package com.p2ptestexercise.ui.activity

import com.p2ptestexercise.ui.IPresenter
import com.p2ptestexercise.ui.IRouter
import com.p2ptestexercise.ui.IView

interface MainActivityContract {
    interface View: IView {
        fun loadingCompleted()
    }
    interface Router: IRouter {
        fun navigateToWallets()
        fun navigateToLogin()
    }
    interface Presenter: IPresenter<View, Router> {
        fun loadData()
    }
}
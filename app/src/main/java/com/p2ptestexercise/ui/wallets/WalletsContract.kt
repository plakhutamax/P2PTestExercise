package com.p2ptestexercise.ui.wallets

import com.p2ptestexercise.ui.IPresenter
import com.p2ptestexercise.ui.IRouter
import com.p2ptestexercise.ui.IView

interface WalletsContract {
    interface View: IView
    interface Router: IRouter
    interface Presenter: IPresenter<View, Router>
}
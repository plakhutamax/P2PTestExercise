package com.p2ptestexercise.ui.wallets

import com.p2ptestexercise.network.model.Wallet
import com.p2ptestexercise.ui.IPresenter
import com.p2ptestexercise.ui.IRouter
import com.p2ptestexercise.ui.IView

interface WalletsContract {
    interface View : IView {
        fun showLoadingState()
        fun showEmptyState()
        fun showErrorState()
        fun updateData(data: List<Wallet>)
    }

    interface Router : IRouter
    interface Presenter : IPresenter<View, Router> {
        fun refreshData()
        fun loadData()
    }
}
package com.p2ptestexercise.ui.wallets

import com.p2ptestexercise.domain.wallet.WalletInteractor
import com.p2ptestexercise.ui.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WalletsPresenter(
    private val walletInteractor: WalletInteractor
) : BasePresenter<WalletsContract.View, WalletsContract.Router>(),
    WalletsContract.Presenter {

    /**
     * For now we don't have any update live update errors, so we OK with 3 simple states
     * Later better provide some complex states, especially for error handling
     * (error for no data at all and data update error with normal state)
     */
    override fun loadData() {
        launch {
            val wallets = try {
                val wallets = withContext(Dispatchers.IO) {
                    walletInteractor.fetchWallets()
                }
                if (wallets.isEmpty()) {
                    view.showEmptyState()
                } else {
                    view.updateData(wallets)
                }
            } catch (e: Throwable) {
                view.showErrorState()
            }
        }
    }

    override fun refreshData() {
        view.showLoadingState()
        loadData()
    }
}
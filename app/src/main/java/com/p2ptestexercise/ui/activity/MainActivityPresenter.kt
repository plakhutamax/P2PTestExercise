package com.p2ptestexercise.ui.activity

import com.p2ptestexercise.domain.mainactivity.MainActivityAccountInteractor
import com.p2ptestexercise.ui.BasePresenter
import kotlinx.coroutines.*

class MainActivityPresenter(
    private val interactor: MainActivityAccountInteractor
) : BasePresenter<MainActivityContract.View, MainActivityContract.Router>(),
    MainActivityContract.Presenter {

    companion object {
        private const val MINIMUM_DELAY = 1000L
    }

    override fun loadData() {
        launch(Dispatchers.IO) {
            val timeout = async {
                delay(MINIMUM_DELAY)
                return@async true
            }
            val accountRead = async { interactor.isAccountLoaded() }
            // Since loading performs while object is creating we just need to check if account is present
            val (timeoutResult, isAccountLoaded) = awaitAll(timeout, accountRead)

            withContext(Dispatchers.Main) {
                view.loadingCompleted()

                if (isAccountLoaded) {
                    router.navigateToWallets()
                } else {
                    router.navigateToLogin()
                }
            }
        }
    }
}
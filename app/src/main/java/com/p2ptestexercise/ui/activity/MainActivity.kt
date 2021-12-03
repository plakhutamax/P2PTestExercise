package com.p2ptestexercise.ui.activity

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.p2ptestexercise.R
import com.p2ptestexercise.databinding.ActivityMainBinding
import com.p2ptestexercise.ui.MainRouter
import com.p2ptestexercise.ui.login.LoginScreen
import com.p2ptestexercise.ui.wallets.WalletsScreen
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope

class MainActivity :
    FragmentActivity(),
    MainActivityContract.View,
    MainActivityContract.Router,
    MainRouter,
    AndroidScopeComponent {

    override val scope: Scope by activityScope()

    private val presenter: MainActivityContract.Presenter by inject()

    private lateinit var binding: ActivityMainBinding
    private val delayPreDrawListener = ViewTreeObserver.OnPreDrawListener { false }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        presenter.bind(
            view = this,
            router = this,
            lifecycleOwner = this
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.viewTreeObserver.addOnPreDrawListener(delayPreDrawListener)
        presenter.loadData()
    }

    override fun loadingCompleted() {
        binding.root.viewTreeObserver.removeOnPreDrawListener(delayPreDrawListener)
    }

    override fun navigateTo(destination: MainRouter.Destination) {
        val destinationFragment = when (destination) {
            MainRouter.Destination.Wallets -> WalletsScreen.newInstance()
            MainRouter.Destination.Login -> LoginScreen.newInstance()
        }

        supportFragmentManager.commit {
            replace(R.id.fragment_container, destinationFragment)
            setReorderingAllowed(true)
        }
    }

    override fun navigateToWallets() {
        navigateTo(MainRouter.Destination.Wallets)
    }

    override fun navigateToLogin() {
        navigateTo(MainRouter.Destination.Login)
    }

}
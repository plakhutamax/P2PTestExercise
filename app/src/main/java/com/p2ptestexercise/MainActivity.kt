package com.p2ptestexercise

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.p2ptestexercise.ui.wallets.WalletsScreen

class MainActivity : FragmentActivity(), MainRouter {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateTo(destination: MainRouter.Destination) {
        val destinationFragment = when (destination) {
            MainRouter.Destination.Wallets -> WalletsScreen.newInstance()
        }

        supportFragmentManager.commit {
            replace(R.id.fragment_container, destinationFragment)
            setReorderingAllowed(true)
        }
    }
}
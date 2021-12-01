package com.p2ptestexercise.ui.wallets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.p2ptestexercise.databinding.WalletsScreenBinding
import com.p2ptestexercise.ui.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.core.scope.Scope

class WalletsScreen :
    BaseFragment<WalletsContract.View, WalletsContract.Router, WalletsContract.Presenter>(),
    WalletsContract.View,
    WalletsContract.Router,
    AndroidScopeComponent {

    companion object {
        fun newInstance() = WalletsScreen()
    }

    override val scope: Scope by fragmentScope()
    override val presenter: WalletsContract.Presenter by inject()

    private var _binding: WalletsScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WalletsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }
}
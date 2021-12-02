package com.p2ptestexercise.ui.wallets

import android.view.LayoutInflater
import android.view.ViewGroup
import com.p2ptestexercise.databinding.WalletItemLayoutBinding
import com.p2ptestexercise.network.model.Wallet
import com.p2ptestexercise.ui.BaseAdapter
import com.p2ptestexercise.ui.BaseViewHolder

class WalletsAdapter : BaseAdapter<Wallet>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Wallet> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WalletItemLayoutBinding.inflate(inflater, parent, false)
        return WalletViewHolder(binding)
    }
}
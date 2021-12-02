package com.p2ptestexercise.ui.wallets

import com.p2ptestexercise.databinding.WalletItemLayoutBinding
import com.p2ptestexercise.network.model.Wallet
import com.p2ptestexercise.ui.BaseViewHolder

class WalletViewHolder(
    private val binding: WalletItemLayoutBinding
) : BaseViewHolder<Wallet>(binding.root) {

    override fun bindTo(item: Wallet) = with(binding) {
        amount.text = item.amount
        pubkey.text = item.publicKey
        mintAddress.text = item.mint
    }
}
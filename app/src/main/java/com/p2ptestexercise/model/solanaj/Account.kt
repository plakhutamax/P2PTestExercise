package com.p2ptestexercise.model.solanaj

import org.bitcoinj.crypto.DeterministicHierarchy
import org.bitcoinj.crypto.HDKeyDerivation
import org.bitcoinj.crypto.HDUtils
import org.bitcoinj.crypto.MnemonicCode

class Account {
    private var keyPair: TweetNaclFast.Signature.KeyPair

    constructor() {
        keyPair = TweetNaclFast.Signature.keyPair()
    }

    constructor(secretKey: ByteArray) {
        keyPair = TweetNaclFast.Signature.keyPair_fromSecretKey(secretKey)
    }

    private constructor(keyPair: TweetNaclFast.Signature.KeyPair) {
        this.keyPair = keyPair
    }

    val publicKey: PublicKey
        get() = PublicKey(keyPair.publicKey)

    val secretKey: ByteArray
        get() = keyPair.secretKey

    companion object {
        /**
         * Derive a Solana account from a Mnemonic generated by the Solana CLI using bip32 Mnemonic with deviation path of
         * m/501H/0H/0/0
         * @param words seed words
         * @param passphrase seed passphrase
         * @return Solana account
         */
        fun fromBip32Mnemonic(words: List<String>): Account {
            val seed = MnemonicCode.toSeed(words, "")
            val masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed)
            val deterministicHierarchy = DeterministicHierarchy(masterPrivateKey)
            val child = deterministicHierarchy[HDUtils.parsePath("M/501H/${0}H/0/0"), true, true]
            val keyPair = TweetNaclFast.Signature.keyPair_fromSeed(child.privKeyBytes)
            return Account(keyPair)
        }
    }
}
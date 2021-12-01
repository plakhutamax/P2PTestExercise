package com.p2ptestexercise.model.solanaj

import org.bitcoinj.core.Base58

class PublicKey {
    private var pubkey: ByteArray

    constructor(pubkey: String) {
        require(pubkey.length >= PUBLIC_KEY_LENGTH) { "Invalid public key input" }
        this.pubkey = Base58.decode(pubkey)
    }

    constructor(pubkey: ByteArray) {
        require(pubkey.size <= PUBLIC_KEY_LENGTH) { "Invalid public key input" }
        this.pubkey = pubkey
    }

    fun toByteArray(): ByteArray {
        return pubkey
    }

    fun toBase58(): String {
        return Base58.encode(pubkey)
    }

    fun equals(pubkey: PublicKey): Boolean {
        return this.pubkey.contentEquals(pubkey.toByteArray())
    }

    override fun toString(): String {
        return toBase58()
    }

    companion object {
        const val PUBLIC_KEY_LENGTH = 32
    }
}
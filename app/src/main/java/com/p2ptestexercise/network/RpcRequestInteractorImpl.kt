package com.p2ptestexercise.network

import com.p2ptestexercise.domain.account.AuthRepository

class RpcRequestInteractorImpl(
    private val authRepository: AuthRepository
) : RpcRequestInteractor {

    companion object {
        private const val PROGRAM_ID = "TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA"
    }

    override val accountPublicKey: String
        get() = requireNotNull(authRepository.getAccount()?.publicKey?.toBase58())

    //TODO: what real program id should be?
    override val programIdKey: String = PROGRAM_ID

}
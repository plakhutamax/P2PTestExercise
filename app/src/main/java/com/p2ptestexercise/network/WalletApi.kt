package com.p2ptestexercise.network

import com.p2ptestexercise.network.model.WalletResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface WalletApi {
    @POST("/")
    @RpcMethod("getTokenAccountsByOwner")
    suspend fun getWallets(@Query("encoding") encoding: String): WalletResponse
}
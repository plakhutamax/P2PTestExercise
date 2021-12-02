package com.p2ptestexercise.network.model

import com.p2ptestexercise.model.solanaj.PublicKey
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure

@Serializable
data class RpcRequest(
    val requestId: String,
    val jsonrpc: String,
    val method: String
)

//interface RpcParameter
//
//object RpcRequestSerializer: KSerializer<RpcRequest> {
//    override fun deserialize(decoder: Decoder): RpcRequest {
//        TODO("Don't need in our case")
//    }
//
//    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("RpcRequest") {
//        element<String>("id")
//        element<String>("jsonrpc")
//        element<String>("method")
//        element<List<Serializable>>("params")
//    }
//
//    override fun serialize(encoder: Encoder, value: RpcRequest) {
//        encoder.encodeStructure(descriptor) {
//            encodeStringElement(descriptor, 0, value.requestId)
//            encodeStringElement(descriptor, 1, value.jsonrpc)
//            encodeStringElement(descriptor, 2, value.method)
//            encode
//        }
//    }
//}

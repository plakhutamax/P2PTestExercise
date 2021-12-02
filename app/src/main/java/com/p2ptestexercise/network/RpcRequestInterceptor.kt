package com.p2ptestexercise.network


import kotlinx.serialization.json.*
import okhttp3.*
import retrofit2.Invocation
import java.util.*

class RpcRequestInterceptor(
    private val requestInteractor: RpcRequestInteractor,
    private val mediaType: MediaType
) : Interceptor {

    companion object {
        private const val KEY_ID = "id"
        private const val KEY_METHOD = "method"
        private const val KEY_JSONRPC_VERSION = "jsonrpc"
        private const val KEY_PARAMS = "params"
        private const val KEY_PROGRAM_ID = "programId"

        private const val JSONRPC_VERSION = "2.0"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request.getAnnotation<RpcMethod>()?.let {
            request = injectRpcFields(request, it.value)
        }
        return chain.proceed(request)

    }

    // Of course it is better to use some @RpcQuery custom annotation to inject
    // request params, but for our purposes this is OK (fast and easy implementation)
    private fun injectRpcFields(request: Request, method: String): Request {
        val originUrl = request.url()
        val jsonPayload = JsonObject(
            mapOf(
                KEY_ID to JsonPrimitive(UUID.randomUUID().toString()),
                KEY_JSONRPC_VERSION to JsonPrimitive(JSONRPC_VERSION),
                KEY_METHOD to JsonPrimitive(method),
                KEY_PARAMS to JsonArray(
                    buildList {
                        add(JsonPrimitive(requestInteractor.accountPublicKey))
                        add(
                            JsonObject(
                                mapOf(
                                    KEY_PROGRAM_ID to JsonPrimitive(requestInteractor.programIdKey)
                                )
                            )
                        )
                        originUrl.queryParameterNames().forEach {
                            add(
                                JsonObject(
                                    mapOf(
                                        it to JsonPrimitive(request.url().queryParameter(it))
                                    )
                                )
                            )
                        }
                    }
                )
            )
        )
        val newBody = RequestBody.create(mediaType, jsonPayload.toString())
        val cleanUrl = with(originUrl.newBuilder()) {
            originUrl.queryParameterNames().forEach {
                removeAllQueryParameters(it)
            }
            build()
        }
        return request.newBuilder()
            .url(cleanUrl)
            .post(newBody)
            .build()
    }

    private inline fun <reified T : Annotation> Request.getAnnotation(): T? =
        tag(Invocation::class.java)?.method()?.getAnnotation(T::class.java)

    private inline fun <reified T : Annotation> Request.isAnnotationPresent() =
        tag(Invocation::class.java)?.method()?.isAnnotationPresent(T::class.java)
}
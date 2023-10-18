package com.caparelli.exception

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class GlobalErrorWebExceptionHandler(
    errorAttributes: ErrorAttributes,
    resourceProperties: WebProperties.Resources,
    applicationContext: ApplicationContext,
    serverCodecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(
    errorAttributes,
    resourceProperties,
    applicationContext
) {

    init {
        super.setMessageWriters(serverCodecConfigurer.writers)
        super.setMessageReaders(serverCodecConfigurer.readers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> {
        return coRouter {
            GET("/exception", ::exceptionHandler)
        }
    }

    private suspend fun exceptionHandler(serverRequest: ServerRequest): ServerResponse {
        return ServerResponse
            .status(HttpStatus.BAD_REQUEST)
            .bodyValueAndAwait(
                getError(serverRequest).localizedMessage
            )
    }

}

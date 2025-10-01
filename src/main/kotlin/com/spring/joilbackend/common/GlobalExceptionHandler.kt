package com.spring.joilbackend.common

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.Instant

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(exception: EntityNotFoundException, request: WebRequest) =
        createErrorResponse(HttpStatus.NOT_FOUND, exception.message ?: "엔티티를 찾을 수 없습니다", request)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(exception: IllegalArgumentException, request: WebRequest) =
        createErrorResponse(HttpStatus.BAD_REQUEST, exception.message ?: "잘못된 요청입니다", request)

    @ExceptionHandler(Exception::class)
    fun handleGeneric(exception: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        logger.error("알 수 없는 오류가 발생했습니다", exception)
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생했습니다", request)
    }

    private fun createErrorResponse(
        status: HttpStatus,
        message: String,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        logger.warn(message)
        val errorResponse = ErrorResponse(
            status = status.value(),
            message = message,
            path = request.getDescription(false).removePrefix("uri="),
            timestamp = Instant.now()
        )
        return ResponseEntity.status(status).body(errorResponse)
    }
}

data class ErrorResponse(
    val status: Int,
    val message: String,
    val path: String,
    val timestamp: Instant
)

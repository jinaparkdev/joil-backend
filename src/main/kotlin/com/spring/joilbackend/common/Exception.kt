package com.spring.joilbackend.common

import arrow.core.Either
import arrow.core.getOrElse

typealias EitherE<T> = Either<Throwable, T>

fun <T> EitherE<T>.orThrow() = this.getOrElse { throw it }

class EntityNotFoundException(message: String) : Exception(message)

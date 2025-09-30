package com.spring.joilbackend.service

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.toOption
import com.spring.joilbackend.common.EitherE
import com.spring.joilbackend.common.EntityNotFoundException
import com.spring.joilbackend.controller.AddUserRequest
import com.spring.joilbackend.entity.UserEntity
import com.spring.joilbackend.model.User
import com.spring.joilbackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    suspend fun register(req: AddUserRequest): EitherE<User> = Either.catch {

        if (!availableLoginId(req.loginId)) {
            throw IllegalArgumentException("이미 사용중인 아이디입니다: ${req.loginId}")
        }

        //TODO: 비밀번호 암호화
        UserEntity(
            loginId = req.loginId,
            password = req.password,
            name = req.name,
            contact = req.contact,
            expiryDate = req.expiryDate,
            note = req.note
        ).apply(repository::save).let(UserEntity::toModel)
    }

    suspend fun findByLoginId(loginId: String): EitherE<User> = Either.catch {
        repository.findByLoginId(loginId).toOption()
            .map(UserEntity::toModel)
            .getOrElse { throw EntityNotFoundException("사용자 정보를 찾을 수 없습니다: $loginId") }
    }

    private fun availableLoginId(loginId: String): Boolean {
        return repository.findByLoginId(loginId) == null
    }
}

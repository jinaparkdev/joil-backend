package com.spring.joilbackend.repository

import com.spring.joilbackend.entity.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<UserEntity, String> {
    fun findByLoginId(loginId: String): UserEntity?
}

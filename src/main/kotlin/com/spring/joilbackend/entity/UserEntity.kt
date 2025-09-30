package com.spring.joilbackend.entity

import arrow.core.toOption
import com.spring.joilbackend.migration.COLLECTION_NAME
import com.spring.joilbackend.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document(COLLECTION_NAME)
data class UserEntity(
    @Id
    val id: String? = null,
    val loginId: String,
    val password: String,
    val name: String,
    val contact: String,
    val expiryDate: Date,
    val note: String? = null
) {
    fun toModel() = User(
        id = id!!,
        name = name,
        contact = contact,
        expiryDate = expiryDate,
        note = note.toOption()
    )
}

@file:Suppress("unused")

package com.spring.joilbackend.migration

import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackExecution
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.index.Index

const val COLLECTION_NAME = "user"
const val LOGIN_ID_INDEX = "loginId"
const val CONTACT_INDEX = "contact"
const val EXPIRY_DATE_INDEX = "expiryDate"
const val NAME_CONTACT_INDEX = "nameContactIndex"

@ChangeUnit(id = "create-user-index", order = "001", author = "jina")
class CreateUserIndex {

    @Execution
    fun createUserIndexes(mongoTemplate: MongoTemplate) {
        mongoTemplate.indexOps(COLLECTION_NAME)
            .createIndex(Index().on(LOGIN_ID_INDEX, Sort.Direction.ASC).unique())

        mongoTemplate.indexOps(COLLECTION_NAME)
            .createIndex(Index().on(CONTACT_INDEX, Sort.Direction.ASC))

        mongoTemplate.indexOps(COLLECTION_NAME)
            .createIndex(Index().on(EXPIRY_DATE_INDEX, Sort.Direction.ASC))

        mongoTemplate.indexOps(COLLECTION_NAME)
            .createIndex(
                Index()
                    .on("name", Sort.Direction.ASC)
                    .on(CONTACT_INDEX, Sort.Direction.ASC)
                    .named(NAME_CONTACT_INDEX)
            )
    }

    @RollbackExecution
    fun rollback(mongoTemplate: MongoTemplate) {
        mongoTemplate.indexOps(COLLECTION_NAME).dropIndex(LOGIN_ID_INDEX)
        mongoTemplate.indexOps(COLLECTION_NAME).dropIndex(CONTACT_INDEX)
        mongoTemplate.indexOps(COLLECTION_NAME).dropIndex(EXPIRY_DATE_INDEX)
        mongoTemplate.indexOps(COLLECTION_NAME).dropIndex(NAME_CONTACT_INDEX)
    }
}

package com.spring.joilbackend.config

import io.mongock.driver.mongodb.springdata.v4.SpringDataMongoV4Driver
import io.mongock.runner.springboot.MongockSpringboot
import org.springframework.boot.ApplicationRunner
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongockConfig {
    @Bean
    fun mongockApplicationRunner(
        mongoTemplate: MongoTemplate,
        applicationContext: ApplicationContext
    ): ApplicationRunner {
        return MongockSpringboot.builder()
            .setDriver(SpringDataMongoV4Driver.withDefaultLock(mongoTemplate))
            .addMigrationScanPackage("com.spring.joilbackend.migration")
            .setSpringContext(applicationContext)
            .buildApplicationRunner()
    }
}

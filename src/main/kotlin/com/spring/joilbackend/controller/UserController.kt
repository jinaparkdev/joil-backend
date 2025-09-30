package com.spring.joilbackend.controller

import com.spring.joilbackend.common.orThrow
import com.spring.joilbackend.model.User
import com.spring.joilbackend.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

data class AddUserRequest(
    val loginId: String,
    val password: String,
    val name: String,
    val contact: String,
    val expiryDate: Date,
    val note: String? = null
)

@RestController
@RequestMapping("/user")
class UserController(private val service: UserService) {

    @PostMapping
    suspend fun register(@RequestBody req: AddUserRequest): ResponseEntity<User> {
        val result = service.register(req).orThrow()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{loginId}")
    suspend fun findByLoginId(@PathVariable loginId: String): ResponseEntity<User> {
        val result = service.findByLoginId(loginId).orThrow()

        return ResponseEntity.ok(result)
    }
}

package br.com.woodriver.hstrackerauth.controller

import br.com.woodriver.hstrackerauth.controller.request.UserRequest
import br.com.woodriver.hstrackerauth.domain.UserEntity
import br.com.woodriver.hstrackerauth.mapper.toDomain
import br.com.woodriver.hstrackerauth.shared.logger
import br.com.woodriver.hstrackerauth.usecase.UserUseCase
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("http://localhost:4200")
class UserController(val userUseCase: UserUseCase) {

    val logger = logger<UserController>()

    @PostMapping(value = ["/signup"])
    fun signup(@RequestBody body: UserRequest):UserEntity {
        logger.info("Starting to create [User={}]", body.email)
        return userUseCase.createUser(body.toDomain())
            .apply {
                logger.info("Done to create user with [ID={}]", this.id)
            }
    }
}
package br.com.woodriver.hstrackerauth.mapper

import br.com.woodriver.hstrackerauth.controller.request.UserRequest
import br.com.woodriver.hstrackerauth.domain.UserEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun UserRequest.toDomain(): UserEntity = UserEntity(email = email, name = name, password = BCryptPasswordEncoder().encode(password),
    role = UserEntity.Role.USER)
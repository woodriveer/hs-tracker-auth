package br.com.woodriver.hstrackerauth.controller.request

data class UserRequest(
    val email: String,
    val password: String
)
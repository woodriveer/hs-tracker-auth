package br.com.woodriver.hstrackerauth.usecase

import br.com.woodriver.hstrackerauth.domain.UserEntity
import br.com.woodriver.hstrackerauth.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserUseCase(val userRepository: UserRepository) {

    fun createUser(userEntity: UserEntity): UserEntity{
        return userRepository.save(userEntity)
    }
}
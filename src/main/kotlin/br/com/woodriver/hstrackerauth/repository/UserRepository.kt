package br.com.woodriver.hstrackerauth.repository

import br.com.woodriver.hstrackerauth.domain.UserEntity
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: PagingAndSortingRepository<UserEntity, Long> {
    fun findUserByEmail(email: String): UserEntity
}
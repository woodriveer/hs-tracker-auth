package br.com.woodriver.hstrackerauth.domain

import javax.persistence.*

@Entity
@Table(name = "HsUser",
    uniqueConstraints = [
        UniqueConstraint(
            columnNames = ["email"]
        )
    ])
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val email: String,
    val password: String,
    val role: Role
) {
    enum class Role {
        ADMIN, USER
    }
}
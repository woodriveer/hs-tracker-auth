package br.com.woodriver.hstrackerauth.security.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(value = "jwt")
class JWTProperties(
    val clientId: String,
    val clientSecret: String,
    val accessTokenValiditySeconds: Int,
    val refreshTokenValiditySeconds: Int
)
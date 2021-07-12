package br.com.woodriver.hstrackerauth.security.configuration

import br.com.woodriver.hstrackerauth.security.properties.JWTProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration(
    val jwtProperties: JWTProperties,
    val userDetailsService: UserDetailsService,
    val authenticationManager: AuthenticationManager,
    val passwordEncoder: PasswordEncoder
): AuthorizationServerConfigurer {
    override fun configure(securityEndpoints: AuthorizationServerSecurityConfigurer) {
        securityEndpoints.tokenKeyAccess("isAnonymous()")
            .checkTokenAccess("permitAll()")
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
            .withClient(jwtProperties.clientId)
            .secret(passwordEncoder.encode(jwtProperties.clientSecret))
            .accessTokenValiditySeconds(jwtProperties.accessTokenValiditySeconds)
            .refreshTokenValiditySeconds(jwtProperties.refreshTokenValiditySeconds)
            .authorizedGrantTypes(*GRANT_TYPES)
            .scopes("read", "write")
            .resourceIds("api")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.accessTokenConverter(accessTokenConverter())
            .userDetailsService(userDetailsService)
            .authenticationManager(authenticationManager)
    }

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter =
        JwtAccessTokenConverter()

    companion object {
        val GRANT_TYPES = arrayOf("password","authorization_code","refresh_token")
    }
}
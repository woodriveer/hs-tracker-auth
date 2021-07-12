package br.com.woodriver.hstrackerauth

import br.com.woodriver.hstrackerauth.security.properties.JWTProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties(JWTProperties::class)
class HsTrackerAuthApplication

fun main(args: Array<String>) {
	runApplication<HsTrackerAuthApplication>(*args)
}

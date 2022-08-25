package br.com.woodriver.hstrackerauth.security.configuration

import br.com.woodriver.hstrackerauth.shared.logger
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class CORSFilter: Filter {

    @Bean
    fun corsFilter(): Filter {
        return CORSFilter()
    }

   override fun doFilter(request: ServletRequest, response: ServletResponse, filter: FilterChain) {
       val responseHttp = response as HttpServletResponse
       val requestHttp = request as HttpServletRequest

       responseHttp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200")
       responseHttp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT")
       responseHttp.setHeader("Access-Control-Max-Age", "3600")
       responseHttp.setHeader("Access-Control-Allow-Headers", "x-hstracker-authorization, x-requested-with, authorization, content-type")

       if ("OPTIONS".equals(requestHttp.method, ignoreCase = true)) {
           logger.info("Enable CORS Successfully")
           responseHttp.status = HttpServletResponse.SC_OK
       } else {
           logger.info("Enable CORS with filters")
           filter.doFilter(request, response)
       }
   }

    companion object {
        val logger = logger<CORSFilter>()
    }
}
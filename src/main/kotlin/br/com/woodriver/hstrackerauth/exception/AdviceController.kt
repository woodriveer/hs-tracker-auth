package br.com.woodriver.hstrackerauth.exception

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.lang.Exception

@ControllerAdvice
class AdviceController {
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleException(ex: Exception, request: WebRequest): ResponseEntity<GenericError> {
        return ResponseEntity(
            GenericError(
                message = "User already exists",
                exception = DataIntegrityViolationException::class.toString()),
            BAD_REQUEST
        )
    }
}
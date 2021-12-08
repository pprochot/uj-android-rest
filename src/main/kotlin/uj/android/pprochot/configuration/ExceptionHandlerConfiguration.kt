package uj.android.pprochot.configuration

import com.fasterxml.jackson.databind.JsonMappingException
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import uj.android.pprochot.exceptions.ResourceNotFoundException
import uj.android.pprochot.exceptions.UserAlreadyExistsException
import uj.android.pprochot.models.dto.ErrorDto

fun Application.configureExceptionHandler() {
    install(StatusPages) {
        jsonMappingException()
        resourceNotFoundException()
    }
}

private fun StatusPages.Configuration.jsonMappingException() = exception<JsonMappingException> {
    call.respond(HttpStatusCode.BadRequest, ErrorDto("INVALID BODY", it.message)) //todo better format it
}

private fun StatusPages.Configuration.resourceNotFoundException() = exception<ResourceNotFoundException> {
    call.respond(HttpStatusCode.NotFound, ErrorDto("MISSING ENTITY", it.message)) //todo error string
}

private fun StatusPages.Configuration.userAlreadyExistsException() = exception<UserAlreadyExistsException> {
    call.respond(HttpStatusCode.NotFound, ErrorDto("USER ALREADY EXISTS", it.message)) //todo error string
}


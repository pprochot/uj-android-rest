package uj.android.pprochot

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import uj.android.pprochot.api.*
import uj.android.pprochot.configuration.DatabaseConfig
import uj.android.pprochot.configuration.configureExceptionHandler
import uj.android.pprochot.mappers.CategoryMapper
import uj.android.pprochot.service.CategoryService

fun main() {
    val databaseConfig = DatabaseConfig()
    databaseConfig.createTables()

    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }
        configureExceptionHandler()
        routing {
            productRouting()
            categoryRoute(CategoryService(CategoryMapper()))
        }
    }.start(wait = true)
}
//read more about how persisted entities are
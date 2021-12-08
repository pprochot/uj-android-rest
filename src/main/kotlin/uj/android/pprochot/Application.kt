package uj.android.pprochot

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers
import com.fasterxml.jackson.databind.module.SimpleModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import uj.android.pprochot.api.*
import uj.android.pprochot.configuration.BigDecimalDeserializer
import uj.android.pprochot.configuration.BigDecimalSerializer
import uj.android.pprochot.configuration.DatabaseConfig
import uj.android.pprochot.configuration.configureExceptionHandler
import uj.android.pprochot.mappers.CategoryMapper
import uj.android.pprochot.service.CategoryService
import java.math.BigDecimal

fun main() {
    val databaseConfig = DatabaseConfig()
    databaseConfig.createTables()

    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
                val module = SimpleModule()
                module.addSerializer(BigDecimal::class.java, BigDecimalSerializer())
                registerModule(SimpleModule())
            }
        }
        configureExceptionHandler()
        routing {
            productRouting()
            categoriesRoute(CategoryService(CategoryMapper()))
        }
    }.start(wait = true)
}
//read more about how persisted entities are
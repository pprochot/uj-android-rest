package uj.android.pprochot.api

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import uj.android.pprochot.models.dto.ProductDto
import uj.android.pprochot.models.entity.Product

fun Route.productRouting() {
    route("/products") {
        get {
            val dtos = transaction {
                Product.all().map {
                    ProductDto(it.id.value, it.name, it.description)
                }
            }
            call.respond(dtos)
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
//            call.respondText { "hejo" }
        }

        post {
            val product = call.receive<ProductDto>()
            transaction {
                Product.new {
                    name = product.name
                    description = product.description
                }
            }
        }
    }
}

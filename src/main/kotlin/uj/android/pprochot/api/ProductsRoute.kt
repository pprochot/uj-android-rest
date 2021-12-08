package uj.android.pprochot.api

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import uj.android.pprochot.models.dto.product.ProductRequest
import uj.android.pprochot.service.ProductService

fun Route.productRoutes(productService: ProductService) {
    route("/products") {
        post {
            val product = call.receive<ProductRequest>()
            val response = productService.create(product)
            call.respond(response)
        }

        get {
            call.respond(productService.getAll())
        }

        route("/{id}") {
            get {
                val id = call.parameters["id"]!!.toIntOrNull()
                    ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid id format!")
                call.respond(productService.getById(id))
            }

            put {
                val id = call.parameters["id"]!!.toIntOrNull()
                    ?: return@put call.respond(HttpStatusCode.BadRequest, "Invalid id format!")
                val product = call.receive<ProductRequest>()
                call.respond(productService.update(id, product))
            }

            delete {
                val id = call.parameters["id"]!!.toIntOrNull()
                    ?: return@delete call.respond(HttpStatusCode.BadRequest, "Invalid id format!")
                productService.delete(id)
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
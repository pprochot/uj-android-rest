package uj.android.pprochot.api

import uj.android.pprochot.service.CartService

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import uj.android.pprochot.models.dto.cart.CartRequest

fun Route.cartRoutes(cartService: CartService) {
    route("/carts") {
        post {
            val order = call.receive<CartRequest>()
            val response = cartService.create(order)
            call.respond(response)
        }

        get {
            call.respond(cartService.getAll())
        }

        route("/{id}") {
            get {
                val id = call.parameters["id"]!!.toIntOrNull()
                    ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid id format!")
                call.respond(cartService.getById(id))
            }

            put {
                val id = call.parameters["id"]!!.toIntOrNull()
                    ?: return@put call.respond(HttpStatusCode.BadRequest, "Invalid id format!")
                val order = call.receive<CartRequest>()
                call.respond(cartService.update(id, order))
            }

            delete {
                val id = call.parameters["id"]!!.toIntOrNull()
                    ?: return@delete call.respond(HttpStatusCode.BadRequest, "Invalid id format!")
                cartService.delete(id)
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
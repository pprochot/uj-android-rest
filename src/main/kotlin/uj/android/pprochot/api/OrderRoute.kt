package uj.android.pprochot.api

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import uj.android.pprochot.models.dto.order.OrderRequest
import uj.android.pprochot.service.OrderService

fun Route.orderRoutes(orderService: OrderService) {
    route("/orders") {
        post {
            val order = call.receive<OrderRequest>()
            val response = orderService.create(order)
            call.respond(response)
        }

        get {
            call.respond(orderService.getAll())
        }

        route("/{id}") {
            get {
                val id = call.parameters["id"]!!.toIntOrNull()
                    ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid id format!")
                call.respond(orderService.getById(id))
            }

            put {
                val id = call.parameters["id"]!!.toIntOrNull()
                    ?: return@put call.respond(HttpStatusCode.BadRequest, "Invalid id format!")
                val order = call.receive<OrderRequest>()
                call.respond(orderService.update(id, order))
            }

            delete {
                val id = call.parameters["id"]!!.toIntOrNull()
                    ?: return@delete call.respond(HttpStatusCode.BadRequest, "Invalid id format!")
                orderService.delete(id)
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
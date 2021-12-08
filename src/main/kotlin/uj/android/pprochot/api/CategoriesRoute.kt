package uj.android.pprochot.api

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import uj.android.pprochot.models.dto.catalog.CategoryRequest
import uj.android.pprochot.models.dto.order.OrderRequest
import uj.android.pprochot.service.CategoryService

fun Route.categoriesRoute(categoryService: CategoryService) {
    route("/categories") {
        post {
            val category = call.receive<CategoryRequest>()
            val response = categoryService.create(category)
            call.respond(response)
        }

        get {
            call.respond(categoryService.getAll())
        }

        route("/{id}") {
            get {
                val id = call.parameters["id"]!!.toIntOrNull()
                    ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid id format!")
                call.respond(categoryService.getById(id))
            }

            put {
                val id = call.parameters["id"]!!.toIntOrNull()
                    ?: return@put call.respond(HttpStatusCode.BadRequest, "Invalid id format!")
                val order = call.receive<CategoryRequest>()
                call.respond(categoryService.update(id, order))
            }

            delete {
                val id = call.parameters["id"]!!.toIntOrNull()
                    ?: return@delete call.respond(HttpStatusCode.BadRequest, "Invalid id format!")
                categoryService.delete(id)
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}

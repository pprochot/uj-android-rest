package uj.android.pprochot.api

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import uj.android.pprochot.models.dto.catalog.CategoryRequest
import uj.android.pprochot.service.CategoryService

fun Route.categoryRoute(categoryService: CategoryService) {
    route("/categories") {
        post {
            val category = call.receive<CategoryRequest>()
            val response = categoryService.createCategory(category)
            call.respond(response)
        }

        get {
            call.respond(categoryService.getAll())
        }

        route("/{id}") {
            get {
                val id = call.parameters["id"]?.toInt()
                call.respond(categoryService.getById(id!!)) //todo null
            }

            put{
                val id = call.parameters["id"]?.toInt()
                val category = call.receive<CategoryRequest>()
                call.respond(categoryService.updateCategory(id!!, category)) //todo null
            }

            delete {
                val id = call.parameters["id"]?.toInt()
                categoryService.deleteCategory(id!!) //todo null
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}

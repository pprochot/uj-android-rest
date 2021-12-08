//package uj.android.pprochot.api
//
//import io.ktor.application.*
//import io.ktor.http.*
//import io.ktor.request.*
//import io.ktor.response.*
//import io.ktor.routing.*
//import uj.android.pprochot.models.dto.catalog.CategoryRequest
//import uj.android.pprochot.models.dto.user.UserRequest
//import uj.android.pprochot.service.UserService
//
//fun Route.usersRoute(usersService: UserService) {
//    route("/users") {
//        post {
//            val category = call.receive<UserRequest>()
//            us
//        }
//
//        get {
//            call.respond(usersService.getAll())
//        }
//
//        route("/{id}") {
//            get {
//                val id = call.parameters["id"]?.toInt()
//                call.respond(usersService.getById(id!!)) //todo null
//            }
//
//            put {
//                val id = call.parameters["id"]?.toInt()
//                val category = call.receive<CategoryRequest>()
//                call.respond(usersService.updateCategory(id!!, category)) //todo null
//            }
//
//            delete {
//                val id = call.parameters["id"]?.toInt()
//                usersService.deleteCategory(id!!) //todo null
//                call.respond(HttpStatusCode.OK)
//            }
//        }
//    }
//}
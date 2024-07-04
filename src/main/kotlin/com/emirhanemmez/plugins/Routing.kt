package com.emirhanemmez.plugins

import com.emirhanemmez.routing.configureBookRouting
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/user") {
            val id = call.request.queryParameters["id"]
            call.respondText { "$id" }
        }

        get("/user/{id}") {
            val id = call.parameters["id"]
            call.respondText { "$id" }
        }
        post("/user") {
            val userId = call.receive<String>()
            call.respondText { userId }
        }

        configureBookRouting()
    }
}

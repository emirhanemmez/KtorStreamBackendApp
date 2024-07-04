package com.emirhanemmez.routing

import com.emirhanemmez.model.Book
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val bookList = mutableListOf(
    Book(1, "A", 10),
    Book(2, "B", 20),
    Book(3, "C", 30),
    Book(4, "D", 40),
)

fun Routing.configureBookRouting() {
    route("/book") {
        get {
            call.respond(bookList)
        }

        get("/{id}") {
            val id = call.parameters["id"]
            val book = bookList.first { it.id.toString() == id }
            call.respond(book)
        }

        post {
            val body = call.receive<Book>()
            bookList.add(body)
            call.respond(bookList)
        }

        delete("/{id}") {
            val id = call.parameters["id"]
            bookList.removeIf { it.id.toString() == id }
            call.respond(bookList)
        }

        put {
            val body = call.receive<Book>()
            val index = bookList.indexOfFirst { it.id == body.id }
            bookList[index] = body
            call.respond(bookList)
        }
    }
}
package com.emirhanemmez.routing

import com.emirhanemmez.database.table.BookTable
import com.emirhanemmez.model.Book
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.configureBookRouting() {
    route("/book") {
        get {
            val bookList = BookTable.getBooks()
            call.respond(bookList)
        }

        get("/{id}") {
            val id = call.parameters["id"]!!
            val book = BookTable.getBookById(id.toInt())
            call.respond(book)
        }

        post {
            val body = call.receive<Book>()
            BookTable.addBook(body)
            val bookList = BookTable.getBooks()
            call.respond(bookList)
        }

        delete("/{id}") {
            val id = call.parameters["id"]!!
            BookTable.deleteBookById(id.toInt())
            val bookList = BookTable.getBooks()
            call.respond(bookList)
        }

        put {
            val body = call.receive<Book>()
            BookTable.editBook(body)
            val bookList = BookTable.getBooks()
            call.respond(bookList)
        }
    }
}
package com.emirhanemmez.database.table

import com.emirhanemmez.model.Book
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object BookTable : Table("book") {
    private val id = integer("id").autoIncrement()
    private val name = varchar("name", 30)
    private val pageNumber = integer("page_number")

    fun getBooks(): List<Book> {
        return transaction {
            BookTable.selectAll()
                .map {
                    it.toBook()
                }
        }
    }

    fun getBookById(id: Int): Book {
        return transaction {
            BookTable.selectAll()
                .where { BookTable.id eq id }
                .map {
                    it.toBook()
                }.first()
        }
    }

    fun addBook(book: Book) {
        transaction {
            BookTable.insert {
                it[name] = book.name
                it[pageNumber] = book.pageNumber
            }
        }
    }

    fun deleteBookById(id: Int) {
        transaction {
            BookTable.deleteWhere {
                BookTable.id eq id
            }
        }
    }

    fun editBook(book: Book) {
        transaction {
            BookTable.update(
                {
                    BookTable.id eq book.id
                }
            ) {
                it[name] = book.name
                it[pageNumber] = book.pageNumber
            }
        }
    }

    private fun ResultRow.toBook(): Book =
        Book(
            id = this[id],
            name = this[name],
            pageNumber = this[pageNumber]
        )
}
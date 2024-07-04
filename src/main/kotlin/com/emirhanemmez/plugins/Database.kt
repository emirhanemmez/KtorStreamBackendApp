package com.emirhanemmez.plugins

import com.emirhanemmez.database.table.BookTable
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase() {
    val dbHost = environment.config.property("ktor.database.db_host").getString()
    val dbPort = environment.config.property("ktor.database.db_port").getString()
    val dbName = environment.config.property("ktor.database.db_name").getString()
    val dbUser = environment.config.property("ktor.database.db_user").getString()
    val dbPassword = environment.config.property("ktor.database.db_password").getString()

    Database.connect(
        url = "jdbc:postgresql://$dbHost:$dbPort/$dbName",
        driver = "org.postgresql.Driver",
        user = dbUser,
        password = dbPassword
    )

    transaction {
        addLogger(StdOutSqlLogger)

        SchemaUtils.create(
            BookTable
        )
    }
}
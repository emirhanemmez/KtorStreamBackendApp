package com.emirhanemmez

import com.emirhanemmez.plugins.configureDatabase
import com.emirhanemmez.plugins.configureRouting
import com.emirhanemmez.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSerialization()
    configureDatabase()
}

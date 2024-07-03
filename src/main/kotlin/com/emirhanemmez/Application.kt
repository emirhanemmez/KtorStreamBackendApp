package com.emirhanemmez

import com.emirhanemmez.plugins.configureRouting
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
}

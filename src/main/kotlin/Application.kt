package com.example

import com.example.database.initDatabase
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import com.example.routes.flashcardRoutes
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.http.content.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureKtor()
    }.start(wait = true)
}

fun Application.module() {
    configureSerialization()
    initDatabase()
    configureRouting()
}

fun Application.configureKtor() {
    install(ContentNegotiation) {
        json()
    }

}

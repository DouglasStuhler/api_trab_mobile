package com.example

import com.example.routes.flashcardRoutes
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        staticResources("static", "static")
        
        flashcardRoutes()
    }
}

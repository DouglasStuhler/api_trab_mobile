package com.example.routes

import com.example.data.dao.FlashcardDao
import com.example.data.table.FlashcardsTable
import com.example.domain.Flashcard
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class JustId(
    val id: Int
)

@Serializable
data class JustTema(
    val tema: String
)

fun Route.flashcardRoutes() {
    val dao = FlashcardDao()

    route("/flashcards") {

        post("/addFlashcard") {
            try {
                val card = call.receive<Flashcard>()
                val insert = dao.insert(card)
                call.respond(HttpStatusCode.Created, mapOf("id" to insert))
            } catch (e: Exception) {
                e.printStackTrace()
                call.respond(HttpStatusCode.InternalServerError, "Erro: ${e.message}")
            }
        }

        post("/delete") {
            val id = call.receive<JustId>()
            val del = dao.delete(id.id)
            call.respond(HttpStatusCode.OK, del)
        }

        post("/addListFlashcard") {
            try {
                val card = call.receive<List<Flashcard>>()
                val insert = dao.insertList(card)
                call.respond(HttpStatusCode.Created, mapOf("id" to insert))
            } catch (e: Exception) {
                e.printStackTrace() // <-- Mostra erro no terminal
                call.respond(HttpStatusCode.InternalServerError, "Erro: ${e.message}")
            }
        }

        post("/getFlashcard") {
            val id = call.receive<JustId>()
            val flashcard = dao.getById(id.id)
            if(flashcard != null) {
                call.respond(HttpStatusCode.OK, flashcard)
            } else {
                call.respond(HttpStatusCode.NoContent, null)
            }
        }

        post("/getFlashcardTema") {
            val string = call.receive<JustTema>()
            val flashcard = dao.getAllByTema(string.tema)
            call.respond(HttpStatusCode.OK, flashcard)
        }

        post("/updateFlashcard") {
            val flashcard = call.receive<Flashcard>()
            val update = dao.update(flashcard)
            call.respond(HttpStatusCode.OK, update)
        }

        get(""){
            val flashcards = dao.getAll()
            call.respond(HttpStatusCode.OK, flashcards)
        }

    }

}
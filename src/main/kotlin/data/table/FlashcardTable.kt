package com.example.data.table

import org.jetbrains.exposed.dao.id.IntIdTable

object FlashcardsTable : IntIdTable("flashcards") {
    val pergunta = text("pergunta")
    val resposta = text("resposta")
    val tipo = integer("tipo")
    val tema = text("tema")
}
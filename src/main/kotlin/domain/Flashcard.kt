package com.example.domain

import kotlinx.serialization.Serializable

@Serializable
data class Flashcard(
    val id: Int = 0,
    val pergunta: String,
    val resposta: String,
    val tipo: Int = 1,
    val tema: String
)

val flashcard = Flashcard(
    id = 1,
    pergunta = "teste 1",
    resposta = "resposta 1",
    tipo = 1,
    tema = "teste"
)

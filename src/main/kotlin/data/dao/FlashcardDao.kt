package com.example.data.dao

import com.example.domain.Flashcard
import com.example.data.table.FlashcardsTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class FlashcardDao {

    fun getById(id: Int): Flashcard? = transaction {
        FlashcardsTable.select { FlashcardsTable.id eq id }
            .map { it.toFlashcard() }
            .singleOrNull()
    }

    fun getAllByTema(tema: String): List<Flashcard> = transaction {
        FlashcardsTable.select { FlashcardsTable.tema eq tema }
            .map { it.toFlashcard() }
    }

    fun getAll(): List<Flashcard> = transaction {
        FlashcardsTable.selectAll().map { it.toFlashcard() }
    }

    fun insert(card: Flashcard): Int = transaction {
        FlashcardsTable.insertAndGetId {
            it[pergunta] = card.pergunta
            it[resposta] = card.resposta
            it[tipo] = card.tipo
            it[tema] = card.tema
        }.value
    }

    fun insertList(cards: List<Flashcard>): List<Int> {
        val inteiros = mutableListOf<Int>()

        for(card in cards){
            inteiros.add(transaction{
                FlashcardsTable.insertAndGetId {
                    it[pergunta] = card.pergunta
                    it[resposta] = card.resposta
                    it[tipo] = card.tipo
                    it[tema] = card.tema
                }.value
            })
        }

        return inteiros
    }

    fun update(card: Flashcard): Boolean = transaction {
        FlashcardsTable.update({ FlashcardsTable.id eq card.id }) {
            it[pergunta] = card.pergunta
            it[resposta] = card.resposta
            it[tipo] = card.tipo
            it[tema] = card.tema
        } > 0
    }

    fun delete(id: Int): Boolean = transaction {
        FlashcardsTable.deleteWhere { FlashcardsTable.id eq id } > 0
    }

    fun ResultRow.toFlashcard() = Flashcard(
        id = this[FlashcardsTable.id].value,
        pergunta = this[FlashcardsTable.pergunta],
        resposta = this[FlashcardsTable.resposta],
        tipo = this[FlashcardsTable.tipo],
        tema = this[FlashcardsTable.tema],
    )

}

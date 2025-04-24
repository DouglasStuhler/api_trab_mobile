package com.example.database

import com.example.data.table.FlashcardsTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun initDatabase() {
    val config = HikariConfig().apply {
        jdbcUrl = "jdbc:sqlite:flashcards.db"
        driverClassName = "org.sqlite.JDBC"
        maximumPoolSize = 5
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_SERIALIZABLE"
    }

    val dataSource = HikariDataSource(config)
    Database.connect(dataSource)

    transaction {
        SchemaUtils.create(FlashcardsTable)
    }
}
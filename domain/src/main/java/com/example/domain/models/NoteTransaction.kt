package com.example.domain.models

data class NoteTransaction(
        val id: String? = null,
        val title: String? = null,
        val content: String? = null,
        val transactionType: TransactionType
)

enum class TransactionType(val value: String) {
    UPDATE( "update"),
    DELETE( "delete"),
}

fun Note.toTransaction(type: TransactionType): NoteTransaction = NoteTransaction(
        id,
        title,
        content,
        type
)

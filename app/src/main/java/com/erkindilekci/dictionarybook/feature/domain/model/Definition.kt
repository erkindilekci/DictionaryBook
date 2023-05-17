package com.erkindilekci.dictionarybook.feature.domain.model

data class Definition(
    val antonyms: List<Any>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
)

package com.erkindilekci.dictionarybook.feature.data.remote.dto

import com.erkindilekci.dictionarybook.feature.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<Any>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
) {
    fun toDefinition() : Definition = Definition(antonyms, definition, example, synonyms)
}
package com.erkindilekci.dictionarybook.feature.data.remote.dto

import com.erkindilekci.dictionarybook.feature.domain.model.Meaning

data class MeaningDto(
    val antonyms: List<String>,
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
    val synonyms: List<String>
) {
    fun toMeaning(): Meaning = Meaning(antonyms, definitions.map { it.toDefinition() }, partOfSpeech, synonyms)
}
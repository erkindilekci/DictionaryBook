package com.erkindilekci.dictionarybook.feature.data.remote.dto

import com.erkindilekci.dictionarybook.feature.data.local.entity.WordInfoEntity
import com.erkindilekci.dictionarybook.feature.domain.model.WordInfo

data class WordInfoDto(
    val license: License,
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
) {
    fun toWordInfoEntity(): WordInfoEntity = WordInfoEntity(word, phonetic, meanings.map { it.toMeaning() })
}
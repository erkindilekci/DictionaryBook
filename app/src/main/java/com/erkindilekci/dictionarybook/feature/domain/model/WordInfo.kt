package com.erkindilekci.dictionarybook.feature.domain.model

import com.erkindilekci.dictionarybook.feature.data.remote.dto.License

data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String,
    val word: String
)

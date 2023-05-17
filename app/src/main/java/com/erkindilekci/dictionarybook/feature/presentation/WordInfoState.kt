package com.erkindilekci.dictionarybook.feature.presentation

import com.erkindilekci.dictionarybook.feature.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)

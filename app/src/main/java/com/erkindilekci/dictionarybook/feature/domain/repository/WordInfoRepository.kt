package com.erkindilekci.dictionarybook.feature.domain.repository

import com.erkindilekci.dictionarybook.feature.util.Resource
import com.erkindilekci.dictionarybook.feature.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}
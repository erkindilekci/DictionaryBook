package com.erkindilekci.dictionarybook.feature.domain.usecase

import com.erkindilekci.dictionarybook.feature.domain.model.WordInfo
import com.erkindilekci.dictionarybook.feature.domain.repository.WordInfoRepository
import com.erkindilekci.dictionarybook.feature.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUseCase(
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow {  }
        }
        return repository.getWordInfo(word)
    }
}
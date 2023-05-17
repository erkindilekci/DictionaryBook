package com.erkindilekci.dictionarybook.feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erkindilekci.dictionarybook.feature.domain.model.Meaning
import com.erkindilekci.dictionarybook.feature.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val word: String,
    val phonetic: String,
    val meanings: List<Meaning>,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    fun toWordInfo(): WordInfo = WordInfo(meanings, phonetic, word)
}

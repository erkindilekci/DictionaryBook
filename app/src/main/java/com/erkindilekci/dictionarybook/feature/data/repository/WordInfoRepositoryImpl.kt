package com.erkindilekci.dictionarybook.feature.data.repository

import com.erkindilekci.dictionarybook.feature.util.Resource
import com.erkindilekci.dictionarybook.feature.data.local.WordInfoDao
import com.erkindilekci.dictionarybook.feature.data.remote.DictionaryApi
import com.erkindilekci.dictionarybook.feature.domain.model.WordInfo
import com.erkindilekci.dictionarybook.feature.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow{
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error("Error: ${e.localizedMessage}", data = wordInfos))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server, check your internet connection!", data = wordInfos))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}
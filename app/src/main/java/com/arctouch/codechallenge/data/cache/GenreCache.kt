package com.arctouch.codechallenge.data.cache

import android.content.SharedPreferences
import com.arctouch.codechallenge.data.entity.GenreEntity
import com.arctouch.codechallenge.data.local.GenreDao
import io.reactivex.Single

class GenreCache(private val genreDao: GenreDao,
                 apiPrefsManager: SharedPreferences) : Cache<GenreEntity>(apiPrefsManager) {

    override fun getAll(): Single<List<GenreEntity>> {
        return Single.create { emitter ->
            emitter.onSuccess(genreDao.getAllGenres())
        }
    }

    override fun putAll(entities: List<GenreEntity>) {
        entities.forEach { put(it) }
    }

    override fun put(entity: GenreEntity) {
        genreDao.insert(entity)
        cache()
    }

    override fun createCachingTag(): String {
        return "com.arctouch.codechallenge.caching.genre"
    }

}
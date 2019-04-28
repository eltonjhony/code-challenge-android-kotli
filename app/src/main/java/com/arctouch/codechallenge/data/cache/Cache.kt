package com.arctouch.codechallenge.data.cache

import android.content.SharedPreferences
import io.reactivex.Single
import java.util.Date

abstract class Cache<T>(private val apiPrefsManager: SharedPreferences) {

    fun hasExpired(): Boolean {
        val lastChecked = apiPrefsManager.getLong(StringBuilder(createCachingTag()).toString(), 0)
        val oneDay = (1000 * 60 * 60 * 24).toLong()
        return lastChecked == 0L || Date().time - lastChecked > oneDay
    }

    fun cache() = apiPrefsManager.edit().putLong(createCachingTag(), Date().time).apply()
    fun invalidate() = apiPrefsManager.edit().putLong(createCachingTag(), 0).apply()

    abstract fun createCachingTag() : String
    abstract fun getAll() : Single<List<T>>
    abstract fun putAll(entities: List<T>)
    abstract fun put(entity: T)
}
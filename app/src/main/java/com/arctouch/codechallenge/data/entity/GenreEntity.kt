package com.arctouch.codechallenge.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreEntity(@PrimaryKey val id: Int, val name: String)
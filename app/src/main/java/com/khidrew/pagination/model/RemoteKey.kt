package com.khidrew.pagination.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey val cardId: String,
    val prevKey: Int?,
    val nextKey: Int?
)
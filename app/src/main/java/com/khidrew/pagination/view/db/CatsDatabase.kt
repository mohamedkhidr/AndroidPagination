package com.khidrew.pagination.view.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.khidrew.pagination.model.Cat
import com.khidrew.pagination.model.RemoteKey

@Database(version = 1, entities = [Cat::class, RemoteKey::class])
abstract class CatsDatabase: RoomDatabase() {
    abstract fun getCatsDao():CatsDao

    abstract fun getKeysDao():RemoteKeyDao
}
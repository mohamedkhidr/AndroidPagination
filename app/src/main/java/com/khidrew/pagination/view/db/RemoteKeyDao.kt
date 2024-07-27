package com.khidrew.pagination.view.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khidrew.pagination.model.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<RemoteKey>)


    @Query("DELETE FROM remote_keys")
    suspend fun deleteAll()

    

    @Query("SELECT * FROM remote_keys WHERE cardId = :id")
    suspend fun remoteKeyById(id:String):RemoteKey?
}
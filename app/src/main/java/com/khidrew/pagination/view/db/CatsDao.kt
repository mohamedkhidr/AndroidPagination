package com.khidrew.pagination.view.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khidrew.pagination.model.Cat

@Dao
interface CatsDao {
    @Query("SELECT * FROM cats")
     fun getAllCats(): PagingSource<Int, Cat>

    @Query("DELETE FROM cats")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cats: List<Cat>)

}
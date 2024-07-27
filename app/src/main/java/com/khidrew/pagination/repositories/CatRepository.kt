package com.khidrew.pagination.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.RemoteMediator
import com.khidrew.pagination.model.Cat
import com.khidrew.pagination.network.CatApi
import com.khidrew.pagination.network.CatPagingSource
import com.khidrew.pagination.view.db.CatsDatabase
import com.khidrew.pagination.view.networkanddb.CatRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 30

class CatRepository @Inject constructor(
    private val api: CatApi,
    private val db: CatsDatabase
) {
    fun getCatsFromNetwork(): Flow<PagingData<Cat>>{
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = (PAGE_SIZE +( 2 * PAGE_SIZE)),
                enablePlaceholders = false
            ),
            pagingSourceFactory = {CatPagingSource(api)}
        ).flow
    }

    fun getCatsFromDb(): Flow<PagingData<Cat>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = (PAGE_SIZE + (2 * PAGE_SIZE)),
                enablePlaceholders = false
            ),
            pagingSourceFactory = { db.getCatsDao().getAllCats() }
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getCatsFromMediator():Flow<PagingData<Cat>>{
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = (PAGE_SIZE + (2 * PAGE_SIZE)),
                enablePlaceholders = false
            ),
            remoteMediator = CatRemoteMediator(api, db),
            pagingSourceFactory = {db.getCatsDao().getAllCats()}
        ).flow
    }

    suspend fun fillWithDummyCats(dummyCats:List<Cat>){
        db.getCatsDao().deleteAll()
        db.getCatsDao().insertAll(dummyCats)
    }

    suspend fun deleteDummyData(){
        db.getCatsDao().deleteAll()
    }
}
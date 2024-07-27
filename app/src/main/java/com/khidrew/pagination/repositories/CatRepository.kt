package com.khidrew.pagination.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.khidrew.pagination.model.Cat
import com.khidrew.pagination.network.CatApi
import com.khidrew.pagination.network.CatPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 30
class CatRepository @Inject constructor(private val api: CatApi) {
    fun getCats(): Flow<PagingData<Cat>>{
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = (PAGE_SIZE +( 2 * PAGE_SIZE)),
                enablePlaceholders = false
            ),
            pagingSourceFactory = {CatPagingSource(api)}
        ).flow
    }
}
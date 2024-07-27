package com.khidrew.pagination.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.khidrew.pagination.model.Cat
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1
class CatPagingSource(private val api: CatApi) : PagingSource<Int, Cat>() {
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val page = params.key?: STARTING_PAGE_INDEX
        return try {
            val response = api.getCatImages(
                page = page,
                size = params.loadSize
            )
            LoadResult.Page(
                data = response,
                prevKey = if(page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        }catch (ex:IOException){
            return LoadResult.Error(ex)
        }catch (ex:HttpException){
            return LoadResult.Error(ex)
        }
    }
}
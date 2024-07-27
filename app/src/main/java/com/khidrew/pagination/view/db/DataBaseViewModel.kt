package com.khidrew.pagination.view.db

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.khidrew.pagination.core.BaseViewModel
import com.khidrew.pagination.model.Cat
import com.khidrew.pagination.repositories.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DataBaseViewModel @Inject constructor(private val catRepository: CatRepository) :
    BaseViewModel() {
    override val dataSource: Flow<PagingData<Cat>> = catRepository.getCatsFromDb()


    fun fillWithDummyCats() {
        val dummyCats = mutableListOf<Cat>()
        for(i in 0..1000){
            dummyCats.add(Cat(i.toString(), "https://cdn2.thecatapi.com/images/ja.jpg"))
        }

        viewModelScope.launch {
            catRepository.fillWithDummyCats(dummyCats)
        }
    }
}
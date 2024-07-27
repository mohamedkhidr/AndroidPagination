package com.khidrew.pagination.view.netwrok

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.khidrew.pagination.core.BaseViewModel
import com.khidrew.pagination.model.Cat
import com.khidrew.pagination.repositories.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
@HiltViewModel
class NetworkViewModel @Inject constructor(private val catRepository: CatRepository) : BaseViewModel(){
     val cats: Flow<PagingData<Cat>> = catRepository.getCats().cachedIn(viewModelScope)



}
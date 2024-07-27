package com.khidrew.pagination.view.networkanddb

import androidx.paging.PagingData
import com.khidrew.pagination.core.BaseViewModel
import com.khidrew.pagination.model.Cat
import com.khidrew.pagination.network.CatApi
import com.khidrew.pagination.repositories.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
@HiltViewModel
class NetworkAndDatabaseViewModel @Inject constructor(private val catRepository: CatRepository)
    :BaseViewModel() {
    override val dataSource = catRepository.getCatsFromMediator()
}
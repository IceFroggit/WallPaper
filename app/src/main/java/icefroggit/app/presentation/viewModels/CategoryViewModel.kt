package icefroggit.app.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import icefroggit.app.data.MainRepository
import icefroggit.app.domain.model.Data
import icefroggit.app.domain.paging.CategoryPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CategoryViewModel constructor(private var categoryID: String) : ViewModel() {
    private val repository = MainRepository()

    var data: MutableLiveData<PagingData<Data>> = MutableLiveData()

    init {
        viewModelScope.launch {
            loadCategoryToRandom(categoryID).collect {
                data.postValue(it)
            }
        }
    }

    private fun loadCategoryToRandom(category: String): Flow<PagingData<Data>> {

        return Pager(config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { CategoryPagingSource(repository.retroService(), category) }
        ).flow.cachedIn(viewModelScope)
    }

}
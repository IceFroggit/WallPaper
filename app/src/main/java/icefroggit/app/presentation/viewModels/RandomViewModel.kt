package icefroggit.app.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import icefroggit.app.data.MainRepository
import icefroggit.app.domain.paging.PopularPagingSource
import icefroggit.app.domain.paging.RandomPagingSource

class RandomViewModel : ViewModel() {
    val repository = MainRepository()
    val randomPage = Pager(config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            RandomPagingSource(repository.retroService())
        }
    ).flow.cachedIn(viewModelScope)
}
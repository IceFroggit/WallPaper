package icefroggit.app.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import icefroggit.app.data.MainRepository
import icefroggit.app.domain.paging.PopularPagingSource

class PopularViewModel: ViewModel() {
    private val repository = MainRepository()

    val popularPage = Pager(config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            PopularPagingSource(repository.retroService())
        }
    ).flow.cachedIn(viewModelScope)
}
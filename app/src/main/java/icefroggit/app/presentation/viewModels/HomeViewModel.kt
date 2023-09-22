package icefroggit.app.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import icefroggit.app.data.MainRepository
import icefroggit.app.domain.paging.HomePagingSource

class HomeViewModel : ViewModel() {
    val repository = MainRepository()
    val homePage = Pager(config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            HomePagingSource(repository.retroService())
        }).flow.cachedIn(viewModelScope)
}
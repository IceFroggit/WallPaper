package icefroggit.app.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import icefroggit.app.domain.model.Data
import icefroggit.app.domain.networking.RetrofitServices

class CategoryPagingSource (private val apiService: RetrofitServices, private val category : String) :
    PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = params.key ?: FIRST_PAGE_INDEX
            val responsePopular = apiService.getCategoryFromApi(nextPage,category)
            LoadResult.Page(
                data = responsePopular.data,
                prevKey = null,
                nextKey = responsePopular.paggination?.next?.page,
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

}
package icefroggit.app.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import icefroggit.app.domain.model.Data
import icefroggit.app.domain.networking.RetrofitServices

class HomePagingSource(
    private val apiService:RetrofitServices
): PagingSource<Int,Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try{
            val nextPage = params?.key ?: FIRST_PAGE_INDEX
            val responseHome = apiService.getHomeFromApi(nextPage)
            LoadResult.Page(
                data = responseHome.data,
                prevKey = null,
                nextKey = responseHome.paggination?.next?.page,
            )
        }catch(e:Exception){
            LoadResult.Error(e)
        }
    }
    companion object{
        private const val FIRST_PAGE_INDEX = 1
    }

}
package com.example.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.network.api.WallpaperApi
import com.example.domain.model.Sorting
import com.example.domain.model.listWallpapers.WallpapersListDetails
import com.example.data.mappers.toWallpapersInfo
import retrofit2.HttpException

internal class WallpapersPagingSource(
    private val wallpaperApi: WallpaperApi,
    private val sorting: Sorting,
    private val query: String?,
    private val purity: String,
    private val categories: String,
    private val onItemsCountChange: (Int?) -> Unit
) : PagingSource<Int, WallpapersListDetails>() {

    override fun getRefreshKey(state: PagingState<Int, WallpapersListDetails>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WallpapersListDetails> {

        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val response = wallpaperApi.getListOfWallpapers(
                searchQuery = query, page = pageNumber, sorting = sorting.value, purity = purity, categories = categories)

            if (response.isSuccessful) {
                val itemsCount = response.body()?.meta?.total
                val lastPage = response.body()?.meta?.lastPage

                onItemsCountChange(itemsCount)
                val result = response.body()?.data?.map { response ->
                    response.toWallpapersInfo()
                } ?: emptyList()

                val prevKey = if (pageNumber > 1) pageNumber - 1 else null
                val nextKey = if (pageNumber >= (lastPage ?: pageNumber)) null else pageNumber + 1

                return LoadResult.Page(result, prevKey, nextKey)
            } else {
                return LoadResult.Error(HttpException(response))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {

        const val INITIAL_PAGE_NUMBER = 1
    }
}
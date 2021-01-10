package xyz.divineugorji.photosearchapp.data

import androidx.paging.PagingSource
import retrofit2.HttpException
import retrofit2.http.Query
import xyz.divineugorji.photosearchapp.api.PhotoItemApi
import java.io.IOException
import java.lang.Exception

private const val PHOTO_STARTING_PAGE_INDEX = 1
class PhotoSearchPagingSource(
    private val photoItemApi: PhotoItemApi,
    private val query: String): PagingSource<Int, PhotoItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoItem> {
        val position  = params.key ?: PHOTO_STARTING_PAGE_INDEX

        return try {
            val response = photoItemApi.searchPhotos(query, position, params.loadSize)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == PHOTO_STARTING_PAGE_INDEX) null else position -1,
                nextKey = if (photos.isEmpty()) null else position + 1

            )
        }catch (exception: IOException){
            LoadResult.Error(exception)
        }catch (exception:HttpException){
            LoadResult.Error(exception)
        }

    }
}
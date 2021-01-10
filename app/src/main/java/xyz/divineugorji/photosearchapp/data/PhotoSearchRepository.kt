package xyz.divineugorji.photosearchapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import xyz.divineugorji.photosearchapp.api.PhotoItemApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoSearchRepository @Inject constructor(private val photoItemApi: PhotoItemApi ) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
           pagingSourceFactory = {
               PhotoSearchPagingSource(photoItemApi, query)
           }
        ).liveData

}
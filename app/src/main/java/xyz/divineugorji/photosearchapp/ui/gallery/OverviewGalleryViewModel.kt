package xyz.divineugorji.photosearchapp.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import xyz.divineugorji.photosearchapp.data.PhotoSearchRepository

class OverviewGalleryViewModel @ViewModelInject constructor(
    private val repository: PhotoSearchRepository): ViewModel() {


    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

        val photos = currentQuery.switchMap { queryString ->
            repository.getSearchResults(queryString)

        }


    fun searchPhotos(query: String){
        currentQuery.value = query

    }

    companion object{
        private const val DEFAULT_QUERY = "cars"
    }

}
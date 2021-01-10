package xyz.divineugorji.photosearchapp.api

import xyz.divineugorji.photosearchapp.data.PhotoItem

data class PhotoResponse(
    val results: List<PhotoItem>
)
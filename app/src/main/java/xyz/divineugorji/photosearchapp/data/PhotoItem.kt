package xyz.divineugorji.photosearchapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoItem(
    val id: String,
    val likes: Int,
    val description: String,
    val urls: PhotoItemUrl,
    val user: PhotoItemUser
):Parcelable {
    @Parcelize
    data class PhotoItemUrl(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String
    ):Parcelable

    @Parcelize
    data class PhotoItemUser(
        val username: String,
        val name: String,
        val bio: String,
        val location: String
    ):Parcelable{
        val attributionUrl get() = "https://unsplash.com/$username?utm_source=PhotoSearchApp&utm_medium=referral"
    }
}
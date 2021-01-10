package xyz.divineugorji.photosearchapp.ui.gallery


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import xyz.divineugorji.photosearchapp.R
import xyz.divineugorji.photosearchapp.data.PhotoItem
import xyz.divineugorji.photosearchapp.databinding.OverviewGalleryBinding
import xyz.divineugorji.photosearchapp.databinding.PhotoSearchItemBinding

class PhotoSearchAdapter: PagingDataAdapter<PhotoItem, PhotoSearchAdapter.PhotoViewHolder>(
    PHOTO_COMPARATOR) {


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)


        if (currentItem != null){
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = PhotoSearchItemBinding.
        inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    class PhotoViewHolder(private val binding: PhotoSearchItemBinding):
        RecyclerView.ViewHolder(binding.root){

            fun bind(photo: PhotoItem){
                binding.apply {
                    Glide.with(itemView)
                        .load(photo.urls.regular)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_broken_image)
                        .into(imageView)

                    textViewUsername.text = photo.user.username
                    photoLikes.text = photo.likes.toString()
                }
            }
        }

    companion object{
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<PhotoItem>() {

            override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem) =
                oldItem == newItem
        }
    }
}
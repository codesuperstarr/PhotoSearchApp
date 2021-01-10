package xyz.divineugorji.photosearchapp.ui.gallery

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import xyz.divineugorji.photosearchapp.R
import xyz.divineugorji.photosearchapp.databinding.OverviewGalleryBinding

@AndroidEntryPoint
class OverviewGalleryFragment: Fragment(R.layout.overview_gallery) {

    private var _binding: OverviewGalleryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<OverviewGalleryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = OverviewGalleryBinding.bind(view)

        val adapter = PhotoSearchAdapter()

        binding.apply {
            galleryRecyclerView.setHasFixedSize(true)
            galleryRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PhotoSearchLoadStateAdapter{adapter.retry()},
                footer = PhotoSearchLoadStateAdapter{adapter.retry()},
            )
        }


        viewModel.photos.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_gallery, menu)


        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null){
                    binding.galleryRecyclerView.scrollToPosition(0)
                    viewModel.searchPhotos(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
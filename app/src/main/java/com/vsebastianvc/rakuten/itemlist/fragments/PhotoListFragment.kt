package com.vsebastianvc.rakuten.itemlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.vsebastianvc.rakuten.R
import com.vsebastianvc.rakuten.databinding.FragmentPhotoListBinding
import com.vsebastianvc.rakuten.itemlist.adapters.CustomPhotosAdapter
import com.vsebastianvc.rakuten.itemlist.viewmodel.ItemListViewModel
import com.vsebastianvc.rakuten.models.Photo
import com.vsebastianvc.rakuten.utils.PHOTO_TITLE
import com.vsebastianvc.rakuten.utils.getNavigationResultLiveData
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Sebastian on 11/29/2020.
 */
class PhotoListFragment : Fragment() {

    private lateinit var customPhotosAdapter: CustomPhotosAdapter
    private lateinit var binding: FragmentPhotoListBinding
    private val viewModel: ItemListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentPhotoListBinding>(
                inflater, R.layout.fragment_photo_list, container, false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customPhotosAdapter = CustomPhotosAdapter(requireContext(), listOf())
        binding.lvPhotos.adapter = customPhotosAdapter
        viewModel.photoPageLiveData.observe(viewLifecycleOwner, Observer {
            customPhotosAdapter.refreshData(it.photos.photo)
        })
        viewModel.updatePhotoList()

        binding.lvPhotos.setOnItemClickListener { _, v, position, _ ->
            val photo = customPhotosAdapter.getItem(position)
            navigateToDetailsScreen(v, photo)
        }
        this.getNavigationResultLiveData<String>(PHOTO_TITLE)?.observe(viewLifecycleOwner, Observer {
            binding.tvTitle.text = it
        })
    }

    private fun navigateToDetailsScreen(view: View, photo: Photo) {
        val direction = PhotoListFragmentDirections.actionPhotoListFragmentToPhotoDetailsFragment(
                photo
        )
        view.findNavController().navigate(direction)
    }
}
package com.vsebastianvc.rakuten.itemdetails.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vsebastianvc.rakuten.R
import com.vsebastianvc.rakuten.databinding.FragmentPhotoDetailsBinding
import com.vsebastianvc.rakuten.itemdetails.viewmodel.ItemDetailsViewModel
import com.vsebastianvc.rakuten.utils.PHOTO_TITLE
import com.vsebastianvc.rakuten.utils.setNavigationResult
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.parameter.parametersOf

/**
 * Created by Sebastian on 11/29/2020.
 */
class PhotoDetailsFragment : Fragment(), KoinComponent {

    private val arguments: PhotoDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentPhotoDetailsBinding
    private val itemDetailsViewModel: ItemDetailsViewModel by viewModel{parametersOf(arguments.photo)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate<FragmentPhotoDetailsBinding>(
                inflater, R.layout.fragment_photo_details, container, false
        ).apply {
            viewModel = itemDetailsViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        val backPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setNavigationResult(itemDetailsViewModel.photoTitleLiveData.value, PHOTO_TITLE)
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedCallback)

        return binding.root
    }
}
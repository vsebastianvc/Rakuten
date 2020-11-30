package com.vsebastianvc.rakuten.itemdetails.di

import com.vsebastianvc.rakuten.itemdetails.viewmodel.ItemDetailsViewModel
import com.vsebastianvc.rakuten.models.Photo
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Sebastian on 11/29/2020.
 */
val itemDetailsModule = module {
    viewModel { (photo: Photo) -> ItemDetailsViewModel(photo) }
}
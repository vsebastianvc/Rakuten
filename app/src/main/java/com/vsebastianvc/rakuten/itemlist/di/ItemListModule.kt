package com.vsebastianvc.rakuten.itemlist.di

import com.vsebastianvc.rakuten.itemlist.viewmodel.ItemListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Sebastian on 11/29/2020.
 */

val itemListModule = module {
    viewModel { ItemListViewModel() }
}
package com.vsebastianvc.rakuten.itemlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vsebastianvc.rakuten.data.PhotoPageServiceApi
import com.vsebastianvc.rakuten.data.getPhotoList
import com.vsebastianvc.rakuten.models.PhotoPage
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Sebastian on 11/29/2020.
 */
class ItemListViewModel : ViewModel(), KoinComponent {

    private val photoPageServiceApi: PhotoPageServiceApi by inject()

    private val _photoPage = MutableLiveData<PhotoPage>()
    val photoPageLiveData: LiveData<PhotoPage>
        get() = Transformations.distinctUntilChanged(_photoPage)

    private val _handleResponseErrors = MutableLiveData<String>()
    val handleResponseErrors: LiveData<String>
        get() = _handleResponseErrors

    fun updatePhotoList() {
        getPhotoList(
                service = photoPageServiceApi,
                method = "flickr.photos.getRecent",
                apiKey = "fee10de350d1f31d5fec0eaf330d2dba",
                page = 1,
                format = "json",
                noJsonCallback = true,
                safeSearch = true,
                { photoPage ->
                    _photoPage.value = photoPage
                }, { error ->
                    _handleResponseErrors.value = error
        })
    }
}
package com.vsebastianvc.rakuten.itemlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vsebastianvc.rakuten.models.PhotoPage
import com.vsebastianvc.rakuten.utils.MockUtils

/**
 * Created by Sebastian on 11/29/2020.
 */
class ItemListViewModel : ViewModel() {

    private val _photoPage = MutableLiveData<PhotoPage>()
    val photoPageLiveData : LiveData<PhotoPage>
        get() = _photoPage

    fun updatePhotoList(){
        _photoPage.value = MockUtils.getMockPhotoList()
    }
}
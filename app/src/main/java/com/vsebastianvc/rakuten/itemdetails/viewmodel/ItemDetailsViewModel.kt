package com.vsebastianvc.rakuten.itemdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vsebastianvc.rakuten.models.Photo
import com.vsebastianvc.rakuten.utils.PhotoUtils.Companion.constructPhotoURL

/**
 * Created by Sebastian on 11/29/2020.
 */
class ItemDetailsViewModel(photo: Photo) : ViewModel() {

    private val _photoImageURL = MutableLiveData<String>()
    val photoImageURLLiveData: LiveData<String>
        get() = _photoImageURL

    private val _photoId = MutableLiveData<String>()
    val photoIdDataLiveData: LiveData<String>
        get() = _photoId

    private val _photoOwner = MutableLiveData<String>()
    val photoOwnerLiveData: LiveData<String>
        get() = _photoOwner

    private val _photoSecret = MutableLiveData<String>()
    val photoSecretLiveData: LiveData<String>
        get() = _photoSecret

    private val _photoServer = MutableLiveData<String>()
    val photoServerLiveData: LiveData<String>
        get() = _photoServer

    private val _photoFarm = MutableLiveData<Int>()
    val photoFarmLiveData: LiveData<Int>
        get() = _photoFarm

    private val _photoTitle = MutableLiveData<String>()
    val photoTitleLiveData: LiveData<String>
        get() = _photoTitle

    private val _photoIsPublic = MutableLiveData<Int>()
    val photoIsPublicLiveData: LiveData<Int>
        get() = _photoIsPublic

    private val _photoIsFriend = MutableLiveData<Int>()
    val photoIsFriendLiveData: LiveData<Int>
        get() = _photoIsFriend

    private val _photoIsFamily = MutableLiveData<Int>()
    val photoIsFamilyLiveData: LiveData<Int>
        get() = _photoIsFamily

    init {
        _photoImageURL.value = constructPhotoURL(photo)
        _photoId.value = photo.id
        _photoOwner.value = photo.owner
        _photoSecret.value = photo.secret
        _photoServer.value = photo.server
        _photoFarm.value = photo.farm
        _photoTitle.value = photo.title
        _photoIsPublic.value = photo.isPublic
        _photoIsFriend.value = photo.isFriend
        _photoIsFamily.value = photo.isFamily
    }
}
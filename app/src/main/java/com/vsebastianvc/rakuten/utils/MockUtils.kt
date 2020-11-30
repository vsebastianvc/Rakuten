package com.vsebastianvc.rakuten.utils

import com.vsebastianvc.rakuten.models.Photo
import com.vsebastianvc.rakuten.models.PhotoList
import com.vsebastianvc.rakuten.models.PhotoPage

/**
 * Created by Sebastian on 11/29/2020.
 */
class MockUtils {

    companion object{
        fun getMockPhotoList(): PhotoPage {
            val photo1 = Photo(id = "48837804368", owner = "183727554@N08", secret = "709b44acf8", server = "65535", farm = 66, title = "Reflections Off a Backcountry Pond (Daylight Blues) One", isPublic = 1, isFriend = 0, isFamily = 0)
            val photo2 = Photo(id = "48837804368", owner = "183727554@N08", secret = "709b44acf8", server = "65535", farm = 66, title = "Reflections Off a Backcountry Pond (Daylight Blues) Two", isPublic = 1, isFriend = 0, isFamily = 0)
            val photo3 = Photo(id = "48837804368", owner = "183727554@N08", secret = "709b44acf8", server = "65535", farm = 66, title = "Reflections Off a Backcountry Pond (Daylight Blues) Three", isPublic = 1, isFriend = 0, isFamily = 0)
            val photo4 = Photo(id = "48837804368", owner = "183727554@N08", secret = "709b44acf8", server = "65535", farm = 66, title = "Reflections Off a Backcountry Pond (Daylight Blues) Four", isPublic = 1, isFriend = 0, isFamily = 0)
            val photo5 = Photo(id = "48837804368", owner = "183727554@N08", secret = "709b44acf8", server = "65535", farm = 66, title = "Reflections Off a Backcountry Pond (Daylight Blues) Five", isPublic = 1, isFriend = 0, isFamily = 0)
            val photo6 = Photo(id = "48837804368", owner = "183727554@N08", secret = "709b44acf8", server = "65535", farm = 66, title = "Reflections Off a Backcountry Pond (Daylight Blues) Six", isPublic = 1, isFriend = 0, isFamily = 0)

            val mutablePhotoList = mutableListOf(photo1, photo2, photo3, photo4, photo5, photo6)

            val photoList = PhotoList(page = 1, pages = 10, perPage = 100, total = 1000, photo = mutablePhotoList)

            return PhotoPage(stat = "ok", photos = photoList)
        }
    }
}
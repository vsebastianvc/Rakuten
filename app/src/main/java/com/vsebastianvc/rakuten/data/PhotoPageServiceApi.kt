package com.vsebastianvc.rakuten.data

import com.vsebastianvc.rakuten.models.PhotoPage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sebastian on 11/30/2020.
 */
interface PhotoPageServiceApi {

    @GET("photos.json")
    fun getPhotoPage(
            @Query("method") method: String,
            @Query("api_key") apiKey: String,
            @Query("page") page: Int,
            @Query("format") format: String,
            @Query("nojsoncallback") noJsonCallback: Boolean,
            @Query("safe_search") safeSearch: Boolean
    ): Call<PhotoPage>
}

fun getPhotoList(
        service: PhotoPageServiceApi,
        method: String,
        apiKey: String,
        page: Int,
        format: String,
        noJsonCallback: Boolean,
        safeSearch: Boolean,
        onSuccess: (photoPage: PhotoPage) -> Unit,
        onError: (error: String) -> Unit
) {
    service.getPhotoPage(method, apiKey, page, format, noJsonCallback, safeSearch).enqueue(object : Callback<PhotoPage> {
        override fun onFailure(call: Call<PhotoPage>, t: Throwable) {
            onError(t.message ?: "Error")
        }

        override fun onResponse(call: Call<PhotoPage>, response: Response<PhotoPage>) {
            if (response.isSuccessful) {
                val photoPage = response.body()
                photoPage?.let { onSuccess(it) }
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        }
    })
}


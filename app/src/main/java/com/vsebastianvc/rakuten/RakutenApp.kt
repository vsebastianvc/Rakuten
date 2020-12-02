package com.vsebastianvc.rakuten

import android.app.Application
import com.vsebastianvc.rakuten.di.networkModule
import com.vsebastianvc.rakuten.itemdetails.di.itemDetailsModule
import com.vsebastianvc.rakuten.itemlist.di.itemListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Sebastian on 11/29/2020.
 */
class RakutenApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RakutenApp)
            modules(listOf(itemListModule, itemDetailsModule, networkModule))
        }
    }
}
package com.vsebastianvc.rakuten.utils

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * Created by Sebastian on 11/29/2020.
 */
fun <T> Fragment.getNavigationResultLiveData(key: String) =
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)

fun <T> Fragment.setNavigationResult(result: T, key: String) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}
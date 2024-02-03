package com.example.terramars.ui.earth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EarthViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "La Terre par Satellite"
    }
    val text: LiveData<String> = _text
}
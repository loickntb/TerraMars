package com.example.terramars.ui.earth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EarthViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Eerth Fragment"
    }
    val text: LiveData<String> = _text
}
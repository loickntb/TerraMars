package com.example.terramars.ui.mars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MarsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is MArs Fragment"
    }
    val text: LiveData<String> = _text
}
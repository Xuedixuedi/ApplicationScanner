package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.RetroInstance
import com.example.myapplication.api.RetroService
import com.example.myapplication.model.RecyclerList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListActivityViewModel : ViewModel() {
    private var recyclerListLiveData: MutableLiveData<RecyclerList> = MutableLiveData()

    fun getRecyclerListObserver(): MutableLiveData<RecyclerList> {
        return recyclerListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi("tiktok")
            recyclerListLiveData.postValue(response)
        }
    }
}
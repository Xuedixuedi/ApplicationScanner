package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.RetroInstance
import com.example.myapplication.api.RetroService
import com.example.myapplication.model.RecyclerList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 为界面准备数据
 */
class ListActivityViewModel : ViewModel() {
    private var recyclerListLiveData: MutableLiveData<RecyclerList> = MutableLiveData()

    fun getRecyclerListObserver(): MutableLiveData<RecyclerList> {
        return recyclerListLiveData
    }

    /**
     * 调用api获取数据
     */
    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi("tiktok")
            recyclerListLiveData.postValue(response)
        }
    }


}
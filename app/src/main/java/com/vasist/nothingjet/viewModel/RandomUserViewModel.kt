package com.vasist.nothingjet.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasist.nothingjet.module.RandomUserModule
import com.vasist.nothingjet.repository.RandomUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomUserViewModel (private val repository: RandomUserRepository): ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getUserList()
        }
    }
    val quotes : LiveData<RandomUserModule>
        get() = repository.quotes
}
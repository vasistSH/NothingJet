package com.vasist.nothingjet.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vasist.nothingjet.repository.RandomUserRepository

class RandomUserViewModelFactory (private val repository: RandomUserRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RandomUserViewModel(repository) as T
    }
}
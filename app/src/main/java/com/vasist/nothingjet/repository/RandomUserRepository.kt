package com.vasist.nothingjet.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vasist.nothingjet.module.RandomUserModule
import com.vasist.nothingjet.network.UserListService

class RandomUserRepository (private val userListService: UserListService) {

    private val quotesLiveData = MutableLiveData<RandomUserModule>()
    val quotes: LiveData<RandomUserModule>
        get() = quotesLiveData

    suspend fun getUserList(){
        val userResults = userListService.getUserList()
        if ( userResults.body()!=null){
            quotesLiveData.postValue(userResults.body())

        }
    }
}
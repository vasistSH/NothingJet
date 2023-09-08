package com.vasist.nothingjet.network

import com.vasist.nothingjet.module.RandomUserModule
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val  url ="https://randomuser.me/api/"

object RetrofitHelper {
    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
interface UserListService {
    @GET("?results=500")
    suspend fun getUserList(): Response<RandomUserModule>
}
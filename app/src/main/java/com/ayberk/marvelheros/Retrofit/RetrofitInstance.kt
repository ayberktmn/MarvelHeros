package com.ayberk.marvelheros.Retrofit


import com.ayberk.marvelheros.Models.Hero
import com.ayberk.marvelheros.Models.HeroItem
import retrofit2.http.GET

interface RetrofitInstance {

    @GET("ae1f687d7e67865a3d217ff719e256f8/raw/592160d562604476acd2d4adfd9d383058c7c558/MarvelLists.json")
    fun getHeros()  : retrofit2.Call<Hero>

    @GET("ae1f687d7e67865a3d217ff719e256f8/raw/592160d562604476acd2d4adfd9d383058c7c558/MarvelLists.json")
    fun getDetails() : retrofit2.Call<Hero>


}
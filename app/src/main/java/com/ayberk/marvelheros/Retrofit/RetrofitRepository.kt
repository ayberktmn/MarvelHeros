package com.ayberk.marvelheros.Retrofit

import androidx.lifecycle.MutableLiveData
import com.ayberk.marvelheros.Models.Hero
import com.ayberk.marvelheros.Models.HeroItem
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository@Inject constructor(private val retroService: RetrofitInstance) {

    fun getHeros(liveData: MutableLiveData<Hero>){
        retroService.getHeros().enqueue(object : retrofit2.Callback<Hero>{
            override fun onResponse(call: Call<Hero>, response: Response<Hero>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Hero>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

    fun getDetails(liveData: MutableLiveData<HeroItem>){
        retroService.getDetails().enqueue(object : retrofit2.Callback<HeroItem>{
            override fun onResponse(call: Call<HeroItem>, response: Response<HeroItem>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<HeroItem>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }
}
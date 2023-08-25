package com.ayberk.marvelheros.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayberk.marvelheros.Models.Hero
import com.ayberk.marvelheros.Models.HeroItem
import com.ayberk.marvelheros.Retrofit.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroViewModel@Inject constructor(private val repository: RetrofitRepository) : ViewModel() {

    var HeroList : MutableLiveData<Hero>
    var DetailsHeroList : MutableLiveData<Hero>

    init {
        HeroList = MutableLiveData()
        DetailsHeroList = MutableLiveData()
    }

    fun getHeroLiveData(): MutableLiveData<Hero>{
        return HeroList
    }
    fun  loadHeros(){
        repository.getHeros(HeroList)
    }

    fun getDetailsHero(): MutableLiveData<Hero>{
        return DetailsHeroList
    }
    fun loadDetails(){
        repository.getDetails(DetailsHeroList)
    }
}
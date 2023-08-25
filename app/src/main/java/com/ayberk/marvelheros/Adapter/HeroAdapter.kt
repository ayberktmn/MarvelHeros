package com.ayberk.marvelheros.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.marvelheros.DetailsFragmentDirections
import com.ayberk.marvelheros.HomeFragment
import com.ayberk.marvelheros.HomeFragmentDirections
import com.ayberk.marvelheros.Models.HeroItem
import com.ayberk.marvelheros.R
import com.ayberk.marvelheros.databinding.HeroItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions


class HeroAdapter() : RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    private var livedata: List<HeroItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val binding = HeroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroViewHolder(binding)
    }
    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(livedata!!.get(position))
        holder.binding.linearLayout.setOnClickListener {
           val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(livedata!![position].id)
               holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {

        if(livedata == null){
            return 0
        }
        else{
            return  livedata!!.size
        }
    }
    inner class HeroViewHolder(val binding: HeroItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: HeroItem) {
            binding.txtHeroName.text = hero.name
            Glide.with(binding.imgHero)
                .load(hero.poster)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(binding.imgHero)
        }
    }
    fun setHeroLive(liveData : List<HeroItem>){
        livedata = liveData
        notifyDataSetChanged()
    }
}
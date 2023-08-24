package com.ayberk.marvelheros.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.marvelheros.Models.Detail
import com.ayberk.marvelheros.databinding.DetailsItemBinding
import com.bumptech.glide.Glide

class DetailsAdapter() : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    private var livedata: List<Detail>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val binding = DetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(livedata!!.get(position))
    }

    override fun getItemCount(): Int {

        if(livedata == null){
            return 0
        }
        else{
            return  livedata!!.size

        }
    }

    inner class DetailsViewHolder(private val binding: DetailsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(details: Detail) {
            binding.txtDetailHero.text = details.name
            Glide.with(binding.imgDetailHero)
                .load(details.poster)
                .into(binding.imgDetailHero)
        }
    }

    fun setDetailLiveData(liveData: List<Detail>) {
        livedata = liveData
        notifyDataSetChanged()
    }
}
package com.ayberk.marvelheros.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.marvelheros.DetailsFragmentDirections
import com.ayberk.marvelheros.Models.Detail
import com.ayberk.marvelheros.R
import com.ayberk.marvelheros.databinding.DetailsItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

class DetailsAdapter() : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    private var livedata: List<Detail>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val binding = DetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(livedata!!.get(position))
        holder.binding.linearLayout2.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToMovieDetailsFragment(livedata!![position].id)
            println("gönderilen detay id: ${livedata!![position].id}")
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

    inner class DetailsViewHolder(val binding: DetailsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(details: Detail) {
            binding.txtDetailHero.text = details.name
            Glide.with(binding.imgDetailHero)
                .load(details.poster)
                .apply(RequestOptions().placeholder(R.drawable.maxresdefault)) // Örnek: Placeholder resim ayarı
                .into(binding.imgDetailHero)
        }
    }

    fun setDetailLiveData(liveData: List<Detail>) {
        livedata = liveData
        notifyDataSetChanged()
    }
}
package com.ayberk.marvelheros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ayberk.marvelheros.Models.Detail
import com.ayberk.marvelheros.Models.HeroItem
import com.ayberk.marvelheros.ViewModel.HeroViewModel
import com.ayberk.marvelheros.databinding.FragmentMovieDetailsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding : FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var MovieList : Detail
    private val viewModel: HeroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchDetailsMovie()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.getDetailsHero().observe(viewLifecycleOwner, Observer { t ->
            if(t != null) {
                arguments?.let {
                    val gelenId = MovieDetailsFragmentArgs.fromBundle(it).movieId
                    println("gelen detay id:   ${gelenId}")
                    MovieList = t[gelenId].details[1]
                    binding.txtMovieName.text = MovieList.name
                    binding.txtDescription.text = MovieList.plot
                    Glide.with(binding.imgMovieDetail)
                        .load(MovieList.poster)
                        .into(binding.imgMovieDetail)
                }

            }
            else{
                println("t null: ${t}")
            }
        })
        return view
    }
    private fun fetchDetailsMovie() {
        CoroutineScope(Dispatchers.Main).async {
            viewModel.loadDetails()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

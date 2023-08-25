package com.ayberk.marvelheros

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ayberk.marvelheros.Adapter.DetailsAdapter
import com.ayberk.marvelheros.Models.Detail
import com.ayberk.marvelheros.ViewModel.HeroViewModel
import com.ayberk.marvelheros.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding : FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DetailsAdapter
    private val viewModel: HeroViewModel by viewModels()
    lateinit var liste : Detail

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        initRecycler()
        fetchDetails()

        val loadingDialog = ProgressDialog(requireContext())
        loadingDialog.show()
        loadingDialog.setContentView(R.layout.loading_dialog)
        loadingDialog.window?.setBackgroundDrawableResource(android.R.color.transparent) // Arka planı şeffaf yap
        loadingDialog.setCancelable(false)

       /* viewModel.getDetailsHero().observe(viewLifecycleOwner, object : Observer<HeroItem> {
            override fun onChanged(t: HeroItem) {
                arguments?.let {
                    val gelen = DetailsFragmentArgs.fromBundle(it).heroPosition
                    liste = t.details[gelen]
                    adapter.setDetailLiveData(t.details)
                    println("gelen: ${gelen}")

                    // detay sayfasının dizaynına göre
                 }
            }
        }) */
        viewModel.getDetailsHero().observe(viewLifecycleOwner, Observer { t ->
            if(t != null) {
                arguments?.let {
                    val gelen = DetailsFragmentArgs.fromBundle(it).heroPosition
                    println("gelen: ${gelen}")
                    adapter.setDetailLiveData((t[gelen].details))
                }
            }
            else{
                println("t null: ${t}")
            }
        })

        Handler(Looper.getMainLooper()).postDelayed({

            loadingDialog.dismiss()

        },1500)
        return view
    }
    private fun initRecycler() {
        adapter = DetailsAdapter()
        binding.RcylerDetails.adapter = adapter
        binding.RcylerDetails.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun fetchDetails() {
        CoroutineScope(Dispatchers.Main).async {
            viewModel.loadDetails()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
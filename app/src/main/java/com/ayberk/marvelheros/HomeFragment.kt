package com.ayberk.marvelheros

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ayberk.marvelheros.Adapter.HeroAdapter
import com.ayberk.marvelheros.ViewModel.HeroViewModel
import com.ayberk.marvelheros.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var isBackPressed = false
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HeroAdapter
    private val viewModel: HeroViewModel by viewModels()
    @Inject
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (sessionManager.getIsFirstRun())
            sessionManager.setIsFirstRun(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            isBackPressed = true
        }
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        initRecycler()
        fetchHeros()

        val loadingDialog = ProgressDialog(requireContext())
        loadingDialog.show()
        loadingDialog.setContentView(R.layout.loading_dialog)
        loadingDialog.window?.setBackgroundDrawableResource(android.R.color.transparent) // Arka planı şeffaf yap
        loadingDialog.setCancelable(false)

        viewModel.getHeroLiveData().observe(viewLifecycleOwner, Observer { t ->
            t?.let {
                adapter.setHeroLive(t)
            }
        })

        Handler(Looper.getMainLooper()).postDelayed({

            loadingDialog.dismiss()

        },750)

        return view
    }

    private fun initRecycler() {
        adapter = HeroAdapter()
        binding.RcyclerHeros.adapter = adapter
        binding.RcyclerHeros.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun fetchHeros() {
        CoroutineScope(Dispatchers.Main).async {
            viewModel.loadHeros()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
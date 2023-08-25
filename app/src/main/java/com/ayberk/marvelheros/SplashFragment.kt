package com.ayberk.marvelheros

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ayberk.marvelheros.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import pl.droidsonroids.gif.GifImageView
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding : FragmentSplashBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater,container,false)
        val view = binding.root

        if(sessionManager.getIsFirstRun()){
            binding.GifImage.setImageResource(R.drawable.splash)
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }, 30000)
        } else{
            binding.GifImage.setImageResource(R.drawable.splashh)
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }, 3000)
        }

        return  view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

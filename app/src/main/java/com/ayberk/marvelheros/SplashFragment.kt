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
import pl.droidsonroids.gif.GifImageView
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding : FragmentSplashBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater,container,false)
        val view = binding.root
        val gifImageView = view.findViewById<GifImageView>(R.id.GifImage)
        Handler(Looper.getMainLooper()).postDelayed({

            if (sessionManager.getIsFirstRun()){
                gifImageView.setImageResource(R.drawable.splash)
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
            else{
                gifImageView.setImageResource(R.drawable.splashh)
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)

            }

        },3000)

        return  view
    }
}
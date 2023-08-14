package com.inxparticle.dfakestore.view.splashScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.inxparticle.dfakestore.R
import com.inxparticle.dfakestore.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    private lateinit var binding : FragmentSplashScreenBinding
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
        binding = FragmentSplashScreenBinding.inflate(inflater)
//        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_splash_screen,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        viewModel.moveToNextScreen.observe(viewLifecycleOwner, Observer {
            if(it){
                Navigation.findNavController(requireActivity(),R.id.navHostFragment).navigate(R.id.action_splashScreenFragment_to_loginScreenFragment)
            }
        })
    }
}
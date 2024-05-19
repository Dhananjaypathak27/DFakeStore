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
import com.inxparticle.dfakestore.util.IS_USER_LOGGED_IN
import com.inxparticle.dfakestore.util.SharedPref

class SplashScreenFragment : Fragment() {
    private lateinit var binding : FragmentSplashScreenBinding
    private val viewModel: SplashScreenViewModel by viewModels()
    private lateinit var sharedPref:SharedPref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSplashScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        sharedPref = SharedPref(requireContext())
        viewModel.moveToNextScreen.observe(viewLifecycleOwner, Observer {
            if(it){
                if(sharedPref.getBoolean(IS_USER_LOGGED_IN))
                    findNavController().graph =  findNavController().navInflater.inflate(R.navigation.logged_in_graph)
                else{
                    findNavController().navigate(R.id.action_splashScreenFragment_to_loginScreenFragment)
                }
//
            }
        })
    }
}
package com.inxparticle.dfakestore.view.homeLoggedIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.inxparticle.dfakestore.R
import com.inxparticle.dfakestore.databinding.FragmentHomeLoggedInBinding
import com.inxparticle.dfakestore.databinding.FragmentLoginScreenBinding
import com.inxparticle.dfakestore.view.loginScreen.LoginScreenViewModel

class HomeLoggedInFragment : Fragment() {

    private lateinit var binding:FragmentHomeLoggedInBinding
    private val viewModel by viewModels<HomeLoggedInViewModel>()

    private val navController by lazy{
        Navigation.findNavController(requireActivity(),R.id.fragment_home_test)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentHomeLoggedInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navViewHome.setupWithNavController(navController)
    }


}
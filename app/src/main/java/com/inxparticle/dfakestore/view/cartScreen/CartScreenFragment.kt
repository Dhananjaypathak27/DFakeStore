package com.inxparticle.dfakestore.view.cartScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.inxparticle.dfakestore.R
import com.inxparticle.dfakestore.databinding.FragmentCartScreenBinding
import com.inxparticle.dfakestore.view.dashboardScreen.DashboardScreenViewModel


class CartScreenFragment : Fragment() {
    val TAG = "CartScreenFragment"

    lateinit var binding:FragmentCartScreenBinding
//    private val viewModel by viewModels<DashboardScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.viewModel = viewModel
        binding.lifecycleOwner = this

//        viewModel.categoryList.observe(viewLifecycleOwner, Observer {
//            Log.e(TAG, "onViewCreated: ${it.size}")
//        })

    }

}
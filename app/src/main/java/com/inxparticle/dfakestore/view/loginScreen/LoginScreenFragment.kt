package com.inxparticle.dfakestore.view.loginScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.inxparticle.dfakestore.R
import com.inxparticle.dfakestore.databinding.FragmentLoginScreenBinding
import kotlinx.coroutines.launch


class LoginScreenFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentLoginScreenBinding
    private val viewModel by viewModels<LoginScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.showError.observe(viewLifecycleOwner, Observer {
            it?.let {
               if(it.isNotEmpty()){
                   Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
               }
            }
        })

        viewModel.moveToLoggedInGraph.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.moveToLoggedInGraph.value = false
//                findNavController().navigate(R.id.action_loginScreenFragment_to_logged_in_graph)

// Get the NavInflater
                val navInflater = findNavController().navInflater

// Inflate the new NavGraph
                val newGraph = navInflater.inflate(R.navigation.logged_in_graph)

// Set the new NavGraph
                findNavController().graph = newGraph

            }
        })
    }

    override fun onClick(view: View?) {

    }


}
package com.inxparticle.dfakestore.view.loginScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.inxparticle.dfakestore.R
import com.inxparticle.dfakestore.databinding.FragmentLoginScreenBinding


class LoginScreenFragment : Fragment(),View.OnClickListener {

    private lateinit var binding:FragmentLoginScreenBinding
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
        binding.listner = this

        viewModel.mShowError.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.moveToDashBoardScreen.observe(viewLifecycleOwner, Observer {
            if(it){
                findNavController().navigate(R.id.action_loginScreenFragment_to_logged_in_graph)
               }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id){
            binding.button.id ->{

                findNavController().navigate(R.id.action_loginScreenFragment_to_logged_in_graph)
            }
        }
    }


}
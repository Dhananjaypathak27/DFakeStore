package com.inxparticle.dfakestore.view.dashboardScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.inxparticle.dfakestore.R
import com.inxparticle.dfakestore.adapter.CategoryAdapter
import com.inxparticle.dfakestore.api.model.category.CategoryResponse
import com.inxparticle.dfakestore.databinding.FragmentDashboardScreenBinding
import com.inxparticle.dfakestore.listener.HomeRecyclerviewListener

class DashboardScreenFragment : Fragment(),HomeRecyclerviewListener {

    lateinit var binding:FragmentDashboardScreenBinding
    private val viewModel by viewModels<DashboardScreenViewModel>()
    lateinit var categoryAdapter:CategoryAdapter
     var list = CategoryResponse()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TAG", "onCreate: dashboardfragment", )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("TAG", "onCreateView: dashboardfragment", )
        binding = FragmentDashboardScreenBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        categoryAdapter = CategoryAdapter(list,this@DashboardScreenFragment)

        binding.recyclerView.apply {
            this.adapter = categoryAdapter
            this.setHasFixedSize(true)
            this.layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)

        }

        viewModel.categoryList.observe(viewLifecycleOwner, Observer {
            list.clear()
            list.addAll(it)
            categoryAdapter.notifyDataSetChanged()
        })



    }

    override fun itemClick(position: Int) {
        Toast.makeText(requireActivity(), "$position", Toast.LENGTH_SHORT).show()
    }
}
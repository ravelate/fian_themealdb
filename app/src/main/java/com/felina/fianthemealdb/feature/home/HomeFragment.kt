package com.felina.fianthemealdb.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.felina.fianthemealdb.R
import com.felina.fianthemealdb.core.ui.AreaAdapter
import com.felina.fianthemealdb.core.ui.CategoryAdapter
import com.felina.fianthemealdb.databinding.FragmentHomeBinding
import com.felina.moviefianapp.core.data.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModel()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter()
        val areaAdapter = AreaAdapter()

        homeViewModel.category.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    categoryAdapter.setData(it.data)
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.viewError.root.visibility = View.VISIBLE
                    binding.viewError.tvError.text = getString(R.string.something_wrong)
                }
            }
        }
        homeViewModel.area.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    areaAdapter.setData(it.data)
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.viewError.root.visibility = View.VISIBLE
                    binding.viewError.tvError.text = getString(R.string.something_wrong)
                }
            }
        }

        with(binding.rvArea) {
            val layout = StaggeredGridLayoutManager(2,LinearLayoutManager.HORIZONTAL)
            layoutManager = layout
            setHasFixedSize(true)
            adapter = areaAdapter
        }
        with(binding.rvCategory) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = categoryAdapter
        }

    }
}
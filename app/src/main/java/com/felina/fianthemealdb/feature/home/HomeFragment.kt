package com.felina.fianthemealdb.feature.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.felina.fianthemealdb.R
import com.felina.fianthemealdb.core.domain.model.Meal
import com.felina.fianthemealdb.core.ui.AreaAdapter
import com.felina.fianthemealdb.core.ui.CategoryAdapter
import com.felina.fianthemealdb.databinding.FragmentHomeBinding
import com.felina.fianthemealdb.feature.detail.DetailFragment
import com.felina.fianthemealdb.feature.meal.MealFragment
import com.felina.fianthemealdb.feature.search.SearchFragment
import com.felina.moviefianapp.core.data.Resource
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.log

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
        (activity as AppCompatActivity).supportActionBar?.show()
        val categoryAdapter = CategoryAdapter()
        val areaAdapter = AreaAdapter()

        categoryAdapter.onItemClick = { selectedData ->
            goToMeal(selectedData.strCategory, "category")
        }

        areaAdapter.onItemClick = { selectedData ->
            goToMeal(selectedData.strArea, "area")
        }

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
        homeViewModel.area.observeForever {
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

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.textView.text = searchView.text
                    searchView.hide()
                    false
                }
            searchBar.inflateMenu(R.menu.option_menu)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    val findMeal = searchView.text
                    goToDetail(findMeal.toString())
                    false
                }
            searchBar.setOnMenuItemClickListener {
                // Handle menuItem click.
                when (it.itemId) {
                    R.id.favorite -> {
                        goToFavorite("Chicken", "favorite")
                      true
                    }
                    else -> true
                }
            }
        }

    }

    fun goToMeal(name: String, type: String){
        val mealFragment = MealFragment()
        val bundle = Bundle()
        bundle.putString(MealFragment.EXTRA_NAME, name)
        bundle.putString(MealFragment.EXTRA_TYPE, type)
        mealFragment.arguments = bundle
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(this@HomeFragment.id, mealFragment, MealFragment::class.java.simpleName)
            addToBackStack(null)
            commit()

        }
    }
    fun goToDetail(meal: String){
        val searchFragment = SearchFragment()
        val bundle = Bundle()
        bundle.putString(SearchFragment.EXTRA_DATA, meal)
        searchFragment.arguments = bundle
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(this@HomeFragment.id, searchFragment, SearchFragment::class.java.simpleName)
            addToBackStack(null)
            commit()

        }
    }
    fun goToFavorite(name: String, type: String){
        val mealFragment = MealFragment()
        val bundle = Bundle()
        bundle.putString(MealFragment.EXTRA_NAME, name)
        bundle.putString(MealFragment.EXTRA_TYPE, type)
        mealFragment.arguments = bundle
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(this@HomeFragment.id, mealFragment, MealFragment::class.java.simpleName)
            addToBackStack(null)
            commit()

        }
    }

}
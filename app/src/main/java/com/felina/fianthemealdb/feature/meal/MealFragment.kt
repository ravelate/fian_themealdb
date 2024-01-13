package com.felina.fianthemealdb.feature.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.felina.fianthemealdb.R
import com.felina.fianthemealdb.core.domain.model.Meal
import com.felina.fianthemealdb.core.ui.MealAdapter
import com.felina.fianthemealdb.databinding.FragmentMealBinding
import com.felina.fianthemealdb.feature.detail.DetailFragment
import com.felina.moviefianapp.core.data.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class MealFragment : Fragment() {

    private var _binding: FragmentMealBinding? = null
    private val mealViewModel: MealViewModel by viewModel()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        if (arguments != null) {
            val name = arguments?.getString(EXTRA_NAME)
            val type = arguments?.getString(EXTRA_TYPE)

            val mealAdapter = MealAdapter()
            mealAdapter.onItemClick = { selectedData ->
                goToDetail(selectedData)
            }
            if (type=="favorite"){
                mealViewModel.favorite.observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            mealAdapter.setData(it.data)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = getString(R.string.something_wrong)
                        }
                    }
                }
            }else {
                mealViewModel.getAllMeal(name,type).observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            mealAdapter.setData(it.data)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvMeal) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = mealAdapter
            }
        }else {
            binding.viewError.tvError.text = getString(R.string.something_wrong)
        }
    }
    fun goToDetail(meal: Meal){
        val detailFragment = DetailFragment()
        val bundle = Bundle()
        bundle.putParcelable(DetailFragment.EXTRA_DATA, meal)
        detailFragment.arguments = bundle
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(this@MealFragment.id, detailFragment, DetailFragment::class.java.simpleName)
            addToBackStack(null)
            commit()

        }
    }
    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_TYPE = "extra_type"
    }

}
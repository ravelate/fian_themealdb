package com.felina.fianthemealdb.feature.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.felina.fianthemealdb.R
import com.felina.fianthemealdb.core.domain.model.Detail
import com.felina.fianthemealdb.databinding.FragmentDetailBinding
import com.felina.moviefianapp.core.data.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val searchViewModel: SearchViewModel by viewModel()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val meal = arguments?.getString(EXTRA_DATA)
            if (meal != null) {
                searchViewModel.getSearchMeal(meal).observe(viewLifecycleOwner){
                    when (it) {
                        is Resource.Loading -> binding.content.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.content.progressBar.visibility = View.GONE
                            it.data?.let { it1 -> showSearchMeal(it1) }
                        }

                        is Resource.Error -> {
                            binding.content.progressBar.visibility = View.GONE
                            binding.content.viewError.root.visibility = View.VISIBLE
                            binding.content.viewError.tvError.text = getString(R.string.something_wrong)
                        }
                    }
                }
            }
        }
    }
    fun showSearchMeal(data: List<Detail>){
        Glide.with(this)
            .load(data.get(0).strMealThumb)
            .into(binding.ivDetailImage)
        binding.content.tvDetailName.text = data.get(0).strMeal
        binding.content.tvDetailCategory.text = data.get(0).strCategory
        binding.content.tvDetailArea.text = data.get(0).strArea
        binding.content.tvDetailInstruction.text = data.get(0).strInstructions

//        var statusFavorite = data.isFavorite
//        setStatusFavorite(statusFavorite)
//        binding.fab.setOnClickListener {
//            statusFavorite = !statusFavorite
//            SearchViewModel.setFavoriteMeal(meal, statusFavorite)
//            setStatusFavorite(statusFavorite)
//        }

    }
    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_not_favorite_white))
        }
    }

    companion object {
        var EXTRA_DATA = "extra_data"
    }
}
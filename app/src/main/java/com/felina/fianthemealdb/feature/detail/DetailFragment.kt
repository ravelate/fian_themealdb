package com.felina.fianthemealdb.feature.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.felina.fianthemealdb.R
import com.felina.fianthemealdb.core.domain.model.Detail
import com.felina.fianthemealdb.core.domain.model.Meal
import com.felina.fianthemealdb.databinding.FragmentDetailBinding
import com.felina.moviefianapp.core.data.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val detailViewModel: DetailViewModel by viewModel()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val meal = arguments?.getParcelable<Meal>(EXTRA_DATA)
            if (meal != null) {
                detailViewModel.getDetailMeal(meal.idMeal.toInt()).observe(viewLifecycleOwner){
                    when (it) {
                        is Resource.Loading -> binding.content.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.content.progressBar.visibility = View.GONE
                            it.data?.let { it1 -> showDetailMeal(it1,meal) }
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
    fun showDetailMeal(data: List<Detail>,meal: Meal){
        Glide.with(this)
            .load(data?.get(0)?.strMealThumb)
            .into(binding.ivDetailImage)
        binding.content.tvDetailName.text = data.get(0).strMeal
        binding.content.tvDetailCategory.text = data.get(0).strCategory
        binding.content.tvDetailArea.text = data.get(0).strArea
        binding.content.tvDetailInstruction.text = data.get(0).strInstructions

        var statusFavorite = meal.isFavorite
        setStatusFavorite(statusFavorite)
        binding.fab.setOnClickListener {
            statusFavorite = !statusFavorite
            detailViewModel.setFavoriteMeal(meal, statusFavorite)
            setStatusFavorite(statusFavorite)
        }

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
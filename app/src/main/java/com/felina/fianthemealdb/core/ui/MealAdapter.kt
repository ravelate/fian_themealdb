package com.felina.fianthemealdb.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.felina.fianthemealdb.R
import com.felina.fianthemealdb.core.domain.model.Meal
import com.felina.fianthemealdb.databinding.ItemListCategoryBinding

class MealAdapter : RecyclerView.Adapter<MealAdapter.ListViewHolder>() {

    private var listData = ArrayList<Meal>()
    var onItemClick: ((Meal) -> Unit)? = null
    fun setData(newListData: List<Meal>?) {
        if (newListData == null) return
        val diffCallback = MealDiffCallback(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        listData = newListData as ArrayList<Meal>
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_category, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListCategoryBinding.bind(itemView)
        fun bind(data: Meal) {
            with(binding) {
                com.bumptech.glide.Glide.with(itemView.context)
                    .load(data.strMealThumb)
                    .into(ivItemImage)
                tvItemTitle.text = data.strMeal
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
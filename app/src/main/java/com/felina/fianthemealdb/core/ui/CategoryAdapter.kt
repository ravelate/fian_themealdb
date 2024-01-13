package com.felina.fianthemealdb.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.felina.fianthemealdb.R
import com.felina.fianthemealdb.core.domain.model.Category
import com.felina.fianthemealdb.databinding.ItemListCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ListViewHolder>() {

    private var listData = ArrayList<Category>()

    fun setData(newListData: List<Category>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
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
        fun bind(data: Category) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.strCategoryThumb)
                    .into(ivItemImage)
                tvItemTitle.text = data.strCategory
            }
        }

    }
}
package com.felina.fianthemealdb.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.felina.fianthemealdb.R
import com.felina.fianthemealdb.core.domain.model.Area
import com.felina.fianthemealdb.core.domain.model.Category
import com.felina.fianthemealdb.databinding.ItemListAreaBinding

class AreaAdapter : RecyclerView.Adapter<AreaAdapter.ListViewHolder>() {

    private var listData = ArrayList<Area>()
    var onItemClick: ((Area) -> Unit)? = null
    fun setData(newListData: List<Area>?) {
        if (newListData == null) return
        val diffCallback = AreaDiffCallback(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        listData = newListData as ArrayList<Area>
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_area, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListAreaBinding.bind(itemView)
        fun bind(data: Area) {
            with(binding) {
                tvTitleArea.text = data.strArea
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
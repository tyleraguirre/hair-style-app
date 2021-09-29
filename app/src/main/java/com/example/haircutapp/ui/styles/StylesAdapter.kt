package com.example.haircutapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.haircutapp.databinding.ListItemHairstyleBinding
import com.example.haircutapp.hairstylesdatabase.Hairstyle
import com.example.haircutapp.ui.styles.StylesViewModel
import kotlinx.android.synthetic.main.list_item_hairstyle.view.*


class StylesAdapter(val viewModel: StylesViewModel): ListAdapter<Hairstyle, StylesAdapter.StylesViewHolder>(StylesListDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StylesAdapter.StylesViewHolder {
        val view =
            ListItemHairstyleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StylesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: StylesAdapter.StylesViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    inner class StylesViewHolder(val binding: ListItemHairstyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            var item = getItem(position)

            binding.mainLayout.setOnClickListener {
                viewModel.setHairstyle(item)
            }

            var moverName = binding.styleName
            var avatar = binding.styleImage

            moverName.text = item.styleName
            avatar.setImageResource(item.styleImage)


        }
    }

}

class StylesListDiffCallback : DiffUtil.ItemCallback<Hairstyle>() {

    override fun areItemsTheSame(oldItem: Hairstyle, newItem: Hairstyle): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Hairstyle, newItem: Hairstyle): Boolean {
        return oldItem.hairstyleId == newItem.hairstyleId
    }

}
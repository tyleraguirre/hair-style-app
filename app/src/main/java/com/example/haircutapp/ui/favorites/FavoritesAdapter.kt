package com.example.haircutapp.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.haircutapp.SharedViewModel
import com.example.haircutapp.StylesAdapter
import com.example.haircutapp.databinding.ListItemHairstyleBinding
import com.example.haircutapp.hairstylesdatabase.Hairstyle

class FavoritesAdapter(val viewModel: SharedViewModel): ListAdapter<Hairstyle, FavoritesAdapter.FavoritesViewHolder>(FavoriteListDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesAdapter.FavoritesViewHolder {
        val view =
            ListItemHairstyleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: FavoritesAdapter.FavoritesViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    inner class FavoritesViewHolder(val binding: ListItemHairstyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            var item = getItem(position)

            binding.mainLayout.setOnClickListener {
                viewModel.setHairstyle(item)
                it.findNavController().navigate(
                    FavoritesFragmentDirections.actionNavigationFavoritesToDetailFragment(item)
                )
            }

            var styleName = binding.styleName
            var styleImage = binding.styleImage

            styleName.text = item.styleName
            styleImage.setImageResource(item.styleImage!!)
        }
    }

}

class FavoriteListDiffCallback : DiffUtil.ItemCallback<Hairstyle>() {

    override fun areItemsTheSame(oldItem: Hairstyle, newItem: Hairstyle): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Hairstyle, newItem: Hairstyle): Boolean {
        return oldItem.hairstyleId == newItem.hairstyleId
    }
}
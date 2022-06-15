package com.example.appcat.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appcat.databinding.CatListItemBinding
import com.squareup.picasso.Picasso

class CatItemAdapter : ListAdapter<Cat, CatItemAdapter.CatItemViewHolder>(DIFF_CALLBACK) {

    // Chamado uma unica vez na criação da lista
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatItemViewHolder {
        val binding = CatListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CatItemViewHolder,
        position: Int
    ) {
        val item = getItem(position)

        Picasso.get().load(item.getImage()).into(holder.binding.catImg)
        holder.binding.textViewCat.text = item.name
    }

    class CatItemViewHolder(
        val binding: CatListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem == newItem
            }
        }
    }
}

package com.example.gitreposmvvm.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.gitreposmvvm.databinding.ItemRepositoryBinding
import com.example.gitreposmvvm.domain.model.Repository

class RepositoryPagingAdapter :
    PagingDataAdapter<Repository, RepositoryPagingAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: ItemRepositoryBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repository?) {

            repo?.let {
                binding.name.text = it.name
                binding.author.text = it.owner
                binding.stars.text = "⭐ ${it.stars}"

                Glide.with(binding.avatar.context)
                    .load(it.avatarUrl)
                    .circleCrop()
                    .into(binding.avatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Repository>() {

        override fun areItemsTheSame(oldItem: Repository, newItem: Repository) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository) =
            oldItem == newItem
    }
}
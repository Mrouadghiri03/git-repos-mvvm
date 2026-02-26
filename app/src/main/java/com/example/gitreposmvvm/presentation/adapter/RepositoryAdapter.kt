/*package com.example.gitreposmvvm.presentation.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitreposmvvm.R
import com.example.gitreposmvvm.domain.model.Repository

// app avec pagination ca marche

class RepositoryAdapter(
    private val items: List<Repository>
) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.avatar)
        val name: TextView = view.findViewById(R.id.name)
        val author: TextView = view.findViewById(R.id.author)
        val stars: TextView = view.findViewById(R.id.stars)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repo = items[position]
        holder.name.text = repo.name
        holder.author.text = "Author: ${repo.owner}"
        holder.stars.text = "⭐ ${repo.stars}"
        Glide.with(holder.avatar.context)
            .load(repo.avatarUrl)
            .circleCrop()
            .into(holder.avatar)
    }

    override fun getItemCount(): Int = items.size
}

 */

package com.example.gitreposmvvm.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitreposmvvm.databinding.ItemRepositoryBinding
import com.example.gitreposmvvm.domain.model.Repository

class RepositoryAdapter(
    private val items: List<Repository>
) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    inner class RepositoryViewHolder(
        private val binding: ItemRepositoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repository) {
            binding.name.text = repo.name
            binding.author.text = "Author: ${repo.owner}"
            binding.stars.text = "⭐ ${repo.stars}"

            Glide.with(binding.avatar.context)
                .load(repo.avatarUrl)
                .circleCrop()
                .into(binding.avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
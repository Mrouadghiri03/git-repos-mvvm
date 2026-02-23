package com.example.gitreposmvvm.presentation.adapter


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



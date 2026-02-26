package com.example.gitreposmvvm.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.gitreposmvvm.R
import com.example.gitreposmvvm.databinding.ItemRepositoryBinding
import com.example.gitreposmvvm.domain.model.Repository

class RepositoryPagingAdapter :
    PagingDataAdapter<Repository, RepositoryPagingAdapter.RepoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        if (repo != null) {
            holder.bind(repo)
        }
    }

    class RepoViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repository) {
            binding.apply {
                // Nom du repository
                name.text = repo.name

                // Nom de l'auteur (ou description si tu préfères)
                author.text = repo.owner // Assure-toi que ce champ existe dans ton modèle

                // Nombre d'étoiles formaté
                stars.text = formatStars(repo.stars)

                // Chargement de l'avatar avec Coil
                avatar.load(repo.avatarUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_placeholder_user) // Image par défaut pendant le chargement
                    error(R.drawable.ic_error_user) // Image si erreur
                    transformations(CircleCropTransformation()) // Force la forme ronde
                }
            }
        }

        private fun formatStars(count: Int): String {
            return if (count >= 1000) {
                "${String.format("%.1f", count / 1000.0)}k"
            } else {
                count.toString()
            }
        }
    }

    /**
     * DiffUtil permet d'actualiser uniquement les items qui ont changé
     * au lieu de rafraîchir toute la liste (très important pour les performances)
     */
    object DiffCallback : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }
    }
}
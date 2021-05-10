package com.example.sdkgit.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sdkgit.R
import com.example.sdkgit.data.model.GitHubModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lista_items_githubb.view.*

class GitHubAdapter(val listaGitHub: MutableList<GitHubModel>) :
    RecyclerView.Adapter<GitHubAdapter.GitAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitAdapterViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.lista_items_githubb, parent, false)
        return GitAdapterViewHolder(view)

    }

    override fun getItemCount() = listaGitHub.count()

    override fun onBindViewHolder(holder: GitAdapterViewHolder, position: Int) {
        holder.bindView(listaGitHub[position])

    }

    fun update(listaUpdate: List<GitHubModel>) {
        listaGitHub.addAll(listaUpdate)
        notifyDataSetChanged()
    }


    class GitAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val repoName = itemView.nameRepo
        private val startCount = itemView.stars
        private val forks = itemView.forks
        private val nomeUser = itemView.nomeUser
        private val imgUSer = itemView.imgUser

        fun bindView(gitHubModel: GitHubModel) {
            repoName.text = gitHubModel.name
            startCount.text = gitHubModel.stargazers_count.toString()
            forks.text = gitHubModel.forks_count.toString()
            nomeUser.text = gitHubModel.gitHubOwner.login
            Picasso.with(itemView.context)
                .load(gitHubModel.gitHubOwner.avatar_url)
                .into(imgUSer)

        }

    }
}
package com.example.android.politicalpreparedness.election.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.politicalpreparedness.databinding.ItemElectionBinding
import com.example.android.politicalpreparedness.models.Election

class ElectionListAdapter(val clickListener: IElectionItemClickListener): ListAdapter<Election, ElectionViewHolder>(ElectionDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
        return ElectionViewHolder.from(parent, clickListener)
    }

    override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
        val itemAtPosition = getItem(position)
        holder.bind(itemAtPosition)
    }

}

class ElectionViewHolder(
    private val binding: ItemElectionBinding,
    private val clickListener: IElectionItemClickListener
    ): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Election) {
        binding.electionItem = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, clickListener: IElectionItemClickListener): ElectionViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemElectionBinding.inflate(layoutInflater, parent, false)
            return ElectionViewHolder(binding, clickListener)
        }
    }

}

class ElectionDiffCallback(): DiffUtil.ItemCallback<Election>() {
    override fun areItemsTheSame(oldItem: Election, newItem: Election): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Election, newItem: Election): Boolean {
        return oldItem == newItem
    }

}
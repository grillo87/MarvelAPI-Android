package com.josegrillo.marvelapi.ui.characterlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.josegrillo.marvelapi.databinding.ItemCharacterBinding
import com.josegrillo.marvelapi.di.modules.GlideRequests
import com.josegrillo.marvelapi.entity.CharacterVO

class CharacterAdapter(
    private val glide: GlideRequests,
    private val onCharacterSelected: (CharacterVO) -> Unit
) :
    PagingDataAdapter<CharacterVO, CharacterViewHolder>(DiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            glide,
            onCharacterSelected
        )
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<CharacterVO>() {
        override fun areItemsTheSame(oldItem: CharacterVO, newItem: CharacterVO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterVO, newItem: CharacterVO): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}
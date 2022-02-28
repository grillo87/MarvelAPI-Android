package com.josegrillo.marvelapi.ui.characterlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.josegrillo.marvelapi.R
import com.josegrillo.marvelapi.databinding.ItemCharacterBinding
import com.josegrillo.marvelapi.di.modules.GlideRequests
import com.josegrillo.marvelapi.entity.CharacterVO

class CharacterViewHolder(
    private val itemCharacterBinding: ItemCharacterBinding,
    private val glide: GlideRequests,
    private val onCharacterSelected: (CharacterVO) -> Unit
) : RecyclerView.ViewHolder(itemCharacterBinding.root) {

    fun bind(item: CharacterVO) {
        itemCharacterBinding.apply {
            root.setOnClickListener {
                onCharacterSelected(item)
            }
            itemCharacterName.text = item.name
            glide.load(item.image)
                .placeholder(R.drawable.ic_noun_iron_man)
                .error(R.drawable.ic_noun_venom)
                .into(itemCharacterImageview)
        }
    }
}
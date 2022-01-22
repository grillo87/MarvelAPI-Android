package com.josegrillo.marvelapi.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.josegrillo.marvelapi.R
import com.josegrillo.marvelapi.databinding.ItemCharacterBinding
import com.josegrillo.marvelapi.di.modules.GlideRequests
import com.josegrillo.marvelapi.entity.CharacterVO
import com.josegrillo.marvelapi.ui.main.MainViewModel

class CharacterAdapter(
    private val glide: GlideRequests,
    private val mainViewModel: MainViewModel
) :
    PagingDataAdapter<CharacterVO, CharacterAdapter.CharacterViewHolder>(DiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            glide,
            mainViewModel::onCharacterSelected
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
}
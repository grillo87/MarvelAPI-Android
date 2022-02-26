package com.josegrillo.marvelapi.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.josegrillo.marvelapi.R
import com.josegrillo.marvelapi.base.BaseFragment
import com.josegrillo.marvelapi.databinding.FragmentDetailBinding
import com.josegrillo.marvelapi.di.modules.GlideApp
import com.josegrillo.marvelapi.utils.getDisplayMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val detailViewModel: DetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCharacterInfo()
    }

    private fun loadCharacterInfo() {
        arguments?.let {
            val args = DetailFragmentArgs.fromBundle(it)
            detailViewModel.loadCharacterDetail(args.characterId)
        }
    }

    override fun configureViewElements() {

        binding.apply {
            errorLayout.errorLayoutRetryButton.setOnClickListener {
                loadCharacterInfo()
            }

            characterFavorite.setOnClickListener {
                arguments?.let {
                    val args = DetailFragmentArgs.fromBundle(it)
                    detailViewModel.updateUserFavoriteStatus(args.characterId)
                }
            }
        }
    }

    override fun setObservers() {

        detailViewModel.errorDisplayer.observe(viewLifecycleOwner, {
            binding.apply {
                progressbar.root.visibility = View.GONE
                errorLayout.root.visibility = View.VISIBLE
            }
            Toast.makeText(
                requireContext(),
                it.getDisplayMessage(),
                Toast.LENGTH_LONG
            ).show()
        })

        detailViewModel.characterFavorite.observe(viewLifecycleOwner, {
            updateCharacterFavoriteStatus(it)
            displayFavoriteUpdatedMessage(it)
        })

        detailViewModel.characterData.observe(this, { characterDetail ->
            binding.apply {
                progressbar.root.visibility = View.GONE
                errorLayout.root.visibility = View.GONE
                updateCharacterFavoriteStatus(characterDetail.isFavorite)
                characterName.text = characterDetail.name
                characterDescription.text =
                    if (!characterDetail.description.isNullOrBlank()) {
                        characterDetail.description
                    } else {
                        getString(R.string.no_description_available)
                    }
                GlideApp.with(this@DetailFragment).load(characterDetail.image)
                    .error(R.drawable.ic_noun_venom)
                    .placeholder(R.drawable.ic_noun_iron_man)
                    .into(headerImageview)
            }
        })
    }

    private fun updateCharacterFavoriteStatus(isFavorite: Boolean) {
        binding.characterFavorite.setImageResource(
            if (isFavorite) {
                R.drawable.ic_spiderman_selected
            } else {
                R.drawable.ic_spiderman_unselected
            }
        )
    }

    private fun displayFavoriteUpdatedMessage(isFavorite: Boolean) {
        Toast.makeText(
            requireContext(),
            getString(
                if (isFavorite) {
                    R.string.successful_favorite_character_added
                } else {
                    R.string.successful_favorite_character_removed
                }
            ), Toast.LENGTH_LONG
        ).show()
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailBinding.inflate(inflater, container, false)
}
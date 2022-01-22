package com.josegrillo.marvelapi.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.josegrillo.marvelapi.R
import com.josegrillo.marvelapi.databinding.ActivityDetailBinding
import com.josegrillo.marvelapi.di.modules.GlideApp
import com.josegrillo.marvelapi.entity.CharacterVO
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object {
        const val ARG_CHARACTER_ID = "ARG_CHARACTER_ID"
        fun launch(
            context: Context,
            characterVO: CharacterVO,
        ) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(ARG_CHARACTER_ID, characterVO.id)
            })
        }
    }

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel<DetailViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUiObservers()
        setObservers()
        configureToolbar()
        loadCharacterData()
    }

    private fun loadCharacterData() {
        (intent.extras?.get(ARG_CHARACTER_ID) as? Int)?.let {
            detailViewModel.loadCharacterDetail(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configureToolbar() {
        setSupportActionBar(binding.activityDetailToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_baseline_arrow_back)?.let {
                setHomeAsUpIndicator(it)
            }
        }
    }

    private fun setUiObservers() {
        binding.apply {
            activityDetailErrorLayout.errorLayoutRetryButton.setOnClickListener {
                loadCharacterData()
            }

            activityDetailCharacterFavorite.setOnClickListener {
                (intent.extras?.get(ARG_CHARACTER_ID) as? Int)?.let {
                    detailViewModel.updateUserFavoriteStatus(it)
                }
            }
        }
    }

    private fun setObservers() {

        detailViewModel.getDetailErrorDisplayer().observe(this, {
            binding.apply {
                activityDetailProgressbar.root.visibility = View.GONE
                activityDetailErrorLayout.root.visibility = View.VISIBLE
            }
        })

        detailViewModel.getCharacterFavoriteErrorDisplayer().observe(this, {
            Toast.makeText(
                this,
                getString(R.string.error_favorite_update_message),
                Toast.LENGTH_LONG
            ).show()
        })

        detailViewModel.getCharacterFavoriteStatusUpdate().observe(this, {
            updateCharacterFavoriteStatus(it)
            displayFavoriteUpdatedMessage(it)
        })

        detailViewModel.getCharacterData().observe(this, { characterDetail ->
            binding.apply {
                activityDetailProgressbar.root.visibility = View.GONE
                activityDetailErrorLayout.root.visibility = View.GONE
                updateCharacterFavoriteStatus(characterDetail.isFavorite)
                activityDetailName.text = characterDetail.name
                activityDetailDescription.text =
                    if (!characterDetail.description.isNullOrBlank()) {
                        characterDetail.description
                    } else {
                        getString(R.string.activity_detail_no_description_available)
                    }
                GlideApp.with(this@DetailActivity).load(characterDetail.image)
                    .error(R.drawable.ic_noun_venom)
                    .placeholder(R.drawable.ic_noun_iron_man)
                    .into(activityDetailHeaderImageview)
            }
        })
    }

    private fun updateCharacterFavoriteStatus(isFavorite: Boolean) {
        binding.activityDetailCharacterFavorite.setImageResource(
            if (isFavorite) {
                R.drawable.ic_spiderman_selected
            } else {
                R.drawable.ic_spiderman_unselected
            }
        )
    }

    private fun displayFavoriteUpdatedMessage(isFavorite: Boolean) {
        Toast.makeText(
            this,
            getString(
                if (isFavorite) {
                    R.string.successful_favorite_character_added
                } else {
                    R.string.successful_favorite_character_removed
                }
            ), Toast.LENGTH_LONG
        ).show()
    }

}
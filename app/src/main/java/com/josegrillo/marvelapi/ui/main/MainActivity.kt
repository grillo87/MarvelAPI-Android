package com.josegrillo.marvelapi.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.paging.LoadState
import com.josegrillo.marvelapi.databinding.ActivityMainBinding
import com.josegrillo.marvelapi.di.modules.GlideApp
import com.josegrillo.marvelapi.ui.detail.DetailActivity
import com.josegrillo.marvelapi.ui.main.adapter.CharacterAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel<MainViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainActivityCharactersList.adapter =
            CharacterAdapter(GlideApp.with(this), mainViewModel)

        mainViewModel.loadData()
        setObservers()
    }

    private fun setObservers() {
        mainViewModel.getCharacterSelectedData().observe(this, {
            DetailActivity.launch(this, it)
        })

        binding.mainActivityErrorView.errorLayoutRetryButton.setOnClickListener {
            mainViewModel.loadData()
        }

        binding.mainActivityFooterContainer.root.setOnClickListener {
            mainViewModel.loadData()
        }

        mainViewModel.getCharactersData().observe(this, {
            (binding.mainActivityCharactersList.adapter as? CharacterAdapter)?.apply {
                binding.apply {
                    mainActivityErrorView.root.visibility = View.GONE
                    mainActivityProgressview.root.visibility = View.GONE
                    mainActivityFooterContainer.root.visibility = View.GONE
                }
                submitData(
                    lifecycle,
                    it
                )
                addLoadStateListener {
                    if (it.refresh is LoadState.Error) {
                        if (binding.mainActivityFooterContainer.root.visibility == View.GONE) {
                            binding.mainActivityErrorView.root.visibility =
                                View.VISIBLE
                        }
                    } else if (it.append is LoadState.Error) {
                        binding.mainActivityFooterContainer.root.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}
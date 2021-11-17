package com.yiqqi.beers.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.yiqqi.beers.R
import com.yiqqi.beers.databinding.FragmentBeerDetailBinding
import com.yiqqi.beers.ui.view.PairingFood
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class BeerDetailFragment : Fragment() {


    private val binding: FragmentBeerDetailBinding by lazy {
        FragmentBeerDetailBinding.inflate(layoutInflater)
    }
    private val viewModel: BeerDetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeViewModel()
    }

    private fun setUpViews() {
        binding.availabilityButton.setOnClickListener {
            viewModel.toggleBeerAvailability()
        }

        binding.myComposable.setContent {
            PairingFood(viewModel.foodPairing)
        }
    }

    private fun observeViewModel() {
        viewModel.run {

            image.observe(viewLifecycleOwner) {
                Glide.with(this@BeerDetailFragment)
                    .load(it)
                    .into(binding.image)
            }

            name.observe(viewLifecycleOwner) {
                binding.name.text = it
            }

            description.observe(viewLifecycleOwner) {
                binding.description.text = it
            }

            tagline.observe(viewLifecycleOwner) {
                binding.tagline.text = it
            }

            ibu.observe(viewLifecycleOwner) {
                binding.ibu.text = it
            }

            abv.observe(viewLifecycleOwner) {
                binding.abv.text = getString(R.string.beer_detail_abv_percentage, it.toString())
            }

            showAvailable.observe(viewLifecycleOwner) {
                binding.availabilityButton.text = getString(if (it.available) R.string.beer_detail_set_unavailable else R.string.beer_detail_set_available)
                changeViewAlpha(binding.image, if (it.available) 1f else 0.5f, it.animate)
                changeViewAlpha(binding.unavailableText, if (it.available) 0f else 1f, it.animate)
            }

        }
    }

    private fun changeViewAlpha(view: View, desiredAlpha: Float, animate: Boolean) {
        view.run {
            if (animate) {
                animate().alpha(desiredAlpha).start()
            } else {
                alpha = desiredAlpha
            }
        }
    }

}
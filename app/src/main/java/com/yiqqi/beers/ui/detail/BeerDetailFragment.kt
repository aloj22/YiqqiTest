package com.yiqqi.beers.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.yiqqi.beers.R
import com.yiqqi.beers.databinding.FragmentBeerDetailBinding
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
                binding.unavailableText.visibility = if (it) View.GONE else View.VISIBLE
                binding.availabilityButton.text = getString(if (it) R.string.beer_detail_set_unavailable else R.string.beer_detail_set_available)
                binding.image.alpha = if (it) 1f else 0.5f
            }

        }
    }

}
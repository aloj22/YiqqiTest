package com.yiqqi.beers.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yiqqi.beers.databinding.ViewItemBeerBinding
import com.yiqqi.beers.domain.Beer

class BeerAdapter(
    private val beers: MutableList<Beer> = mutableListOf(),
    private val onBeerClicked: (Beer) -> Unit
) : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BeerViewHolder(ViewItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(beers[position])
    }

    override fun getItemCount() = beers.size

    fun setItems(newBeers: List<Beer>) {

        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                beers[oldItemPosition].id == newBeers[newItemPosition].id

            override fun getOldListSize() = beers.size

            override fun getNewListSize() = newBeers.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                newBeers[newItemPosition] == beers[oldItemPosition]

        })

        beers.run {
            clear()
            addAll(newBeers)
        }
        diffResult.dispatchUpdatesTo(this)

    }

    inner class BeerViewHolder(private val binding: ViewItemBeerBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(beer: Beer) {
            binding.run {

                name.text = beer.name
                tagline.text = beer.tagline
                unavailableText.visibility = if (beer.available) View.GONE else View.VISIBLE
                binding.image.alpha = if (beer.available) 1f else 0.5f

                Glide.with(image)
                    .load(beer.image)
                    .centerInside()
                    .into(image)

                root.setOnClickListener {
                    onBeerClicked(beer)
                }
            }
        }

    }
}
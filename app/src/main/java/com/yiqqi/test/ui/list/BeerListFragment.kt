package com.yiqqi.test.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.yiqqi.test.R
import com.yiqqi.test.databinding.FragmentBeerListBinding
import com.yiqqi.test.util.setItemOffset
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BeerListFragment : Fragment() {



    private val binding: FragmentBeerListBinding by lazy {
        FragmentBeerListBinding.inflate(layoutInflater)
    }
    private val viewModel: BeerListViewModel by viewModels()
    private val beerAdapter: BeerAdapter by lazy {
        BeerAdapter(onBeerClicked = viewModel::onBeerClicked)
    }

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
        binding.run {
            beersRecyclerView.run {
                adapter = beerAdapter
                val divider = resources.getDimensionPixelSize(R.dimen.default_dimen)
                val spanCount = (layoutManager as? GridLayoutManager)?.spanCount ?: 1
                setItemOffset { rect, position, _ ->
                    val isRightEdge = position % spanCount == spanCount - 1
                    val isLeftEdge = position % spanCount == 0
                    rect.run {
                        right = if (isRightEdge) divider else divider / 2
                        left = if (isLeftEdge) divider else divider / 2
                        top = divider
                    }
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel.run {

            beers.observe(viewLifecycleOwner, beerAdapter::setItems)

            goToBeerDetail.observe(viewLifecycleOwner) {
                findNavController().navigate(BeerListFragmentDirections.openBeerDetail(it))
            }
        }
    }

}
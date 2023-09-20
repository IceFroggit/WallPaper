package icefroggit.app.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import icefroggit.app.R
import icefroggit.app.databinding.FragmentHomeBinding
import icefroggit.app.databinding.FragmentPopularBinding
import icefroggit.app.presentation.adapter.RecyclerViewAdapter
import icefroggit.app.presentation.viewModels.PopularViewModel
import icefroggit.app.utils.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PopularFragment :  BaseFragment<FragmentPopularBinding>(
    FragmentPopularBinding::inflate
) {
    private val viewModel:PopularViewModel by viewModels()
    override fun initViewModel() {
        lifecycleScope.launch(){
            viewModel.popularPage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun initRecyclerView() {
        val layoutManager = GridLayoutManager(context,3)
        binding.popularRecyclerView.layoutManager = layoutManager
        binding.popularRecyclerView.adapter = recyclerViewAdapter
    }
    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(Constants.NavigationIntent.FromHomeToDownload)

}
package icefroggit.app.presentation .fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import icefroggit.app.databinding.FragmentRandomBinding
import icefroggit.app.presentation.adapter.RecyclerViewAdapter
import icefroggit.app.presentation.viewModels.RandomViewModel
import icefroggit.app.utils.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class RandomFragment : BaseFragment<FragmentRandomBinding>(
    FragmentRandomBinding::inflate
) {
    private val viewModel: RandomViewModel by viewModels()
    override fun initViewModel() {
        lifecycleScope.launch {
            viewModel.randomPage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun initRecyclerView() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.randomRecyclerView.layoutManager = layoutManager
        binding.randomRecyclerView.adapter = recyclerViewAdapter
    }

    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(Constants.NavigationIntent.FromHomeToDownload)
}
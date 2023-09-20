package icefroggit.app.presentation.fragments

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import icefroggit.app.databinding.FragmentPopularBinding
import icefroggit.app.domain.model.Data
import icefroggit.app.domain.paging.LoaderStateAdapter
import icefroggit.app.presentation.adapter.RecyclerViewAdapter
import icefroggit.app.presentation.adapter.WallInteractionListener
import icefroggit.app.presentation.viewModels.PopularViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PopularFragment : BaseFragment<FragmentPopularBinding>(
    FragmentPopularBinding::inflate
), WallInteractionListener {
    private val viewModel: PopularViewModel by viewModels()
    override fun initViewModel() {
        lifecycleScope.launch() {
            viewModel.popularPage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun initRecyclerView() {
        val layoutManager = GridLayoutManager(context, 3)
        with(binding) {
            wallRecyclerView.layoutManager = layoutManager
            wallRecyclerView.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
                header = LoaderStateAdapter { recyclerViewAdapter.retry() },
                footer = LoaderStateAdapter { recyclerViewAdapter.retry() }
            )
            recyclerViewAdapter.addLoadStateListener { loadState ->
                wallRecyclerView.isVisible = loadState.refresh is LoadState.NotLoading
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                handleError(loadState)
            }
            buttonRetry.setOnClickListener {
                recyclerViewAdapter.retry()
            }
        }
    }

    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)
    override fun onCLickItem(data: Data, view: View) {
        val imageData = arrayOf(data.fullImageUrl.toString(), data.blurHash.toString())
        Navigation.findNavController(view)
            .navigate(MainFragmentDirections.actionMainFragmentToDownloadFragment(
                imageData))
    }
}
package icefroggit.app.presentation.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import icefroggit.app.databinding.FragmentPopularBinding
import icefroggit.app.domain.model.Data
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
        binding.popularRecyclerView.layoutManager = layoutManager
        binding.popularRecyclerView.adapter = recyclerViewAdapter
    }

    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)
    override fun onCLickItem(data: Data, view: View) {
        val imageData = arrayOf(data.fullImageUrl.toString(), data.blurHash.toString())
        Navigation.findNavController(view)
            .navigate(MainFragmentDirections.actionMainFragmentToDownloadFragment(
                imageData))
    }
}
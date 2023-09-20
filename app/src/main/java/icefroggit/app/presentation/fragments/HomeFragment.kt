package icefroggit.app.presentation.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import icefroggit.app.databinding.FragmentHomeBinding
import icefroggit.app.domain.model.Data
import icefroggit.app.presentation.adapter.RecyclerViewAdapter
import icefroggit.app.presentation.adapter.WallInteractionListener
import icefroggit.app.presentation.viewModels.HomeViewModel
import icefroggit.app.utils.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
), WallInteractionListener {
    private val viewModel: HomeViewModel by viewModels()
    override fun initViewModel() {
        lifecycleScope.launch {
            viewModel.homePage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun initRecyclerView() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.homeRecyclerView.layoutManager = layoutManager
        binding.homeRecyclerView.adapter = recyclerViewAdapter
    }

    override var recyclerViewAdapter: RecyclerViewAdapter =
        RecyclerViewAdapter(this)

    override fun onCLickItem(data: Data, view: View) {
        val imageData = arrayOf(data.fullImageUrl.toString(), data.blurHash.toString())
        Navigation.findNavController(view)
            .navigate(MainFragmentDirections.actionMainFragmentToDownloadFragment(
                imageData))
    }
}
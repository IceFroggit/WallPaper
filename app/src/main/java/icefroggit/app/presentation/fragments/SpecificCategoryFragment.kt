package icefroggit.app.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.GridLayoutManager
import icefroggit.app.databinding.FragmentSpecificCategoryBinding
import icefroggit.app.domain.model.Data
import icefroggit.app.domain.paging.LoaderStateAdapter
import icefroggit.app.presentation.adapter.RecyclerViewAdapter
import icefroggit.app.presentation.adapter.WallInteractionListener
import icefroggit.app.presentation.viewModels.CategoryViewModel
import icefroggit.app.presentation.viewModels.CategoryViewModelFactory
import kotlinx.coroutines.launch

class SpecificCategoryFragment : Fragment(), WallInteractionListener {

    private lateinit var viewModel: CategoryViewModel
    private val args: SpecificCategoryFragmentArgs by navArgs()
    private lateinit var binding: FragmentSpecificCategoryBinding
    private var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSpecificCategoryBinding.inflate(inflater)
        initViewModel()
        recyclerAdapter()
        categoryName()
        callBack()

        return binding.root
    }


    private fun initViewModel() {
        viewModel =
            ViewModelProvider(
                this,
                CategoryViewModelFactory(args.categoryName)
            )[CategoryViewModel::class.java]

        viewModel.data.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    private fun  recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        with(binding) {
            wallCategoriesRecyclerView.layoutManager = layoutManager
            wallCategoriesRecyclerView.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
                header = LoaderStateAdapter { recyclerViewAdapter.retry() },
                footer = LoaderStateAdapter { recyclerViewAdapter.retry() }
            )
            recyclerViewAdapter.addLoadStateListener { loadState ->
                wallCategoriesRecyclerView.isVisible = loadState.refresh is LoadState.NotLoading
                CategoryProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
                CategoryButtonRetry.isVisible = loadState.source.refresh is LoadState.Error
                handelError(loadState)
            }
            CategoryButtonRetry.setOnClickListener {
                recyclerViewAdapter.retry()
            }
        }
    }

    private fun handelError(loadStates: CombinedLoadStates) {
        val errorState = loadStates.source.append as? LoadState.Error
            ?: loadStates.source.prepend as? LoadState.Error

        errorState?.let {
            Toast.makeText(context, "try again later", Toast.LENGTH_LONG).show()
        }
    }

    private fun categoryName() {
        binding.categoryName.text = args.categoryName
    }


    override fun onClickItem(data: Data, view: View) {
        val imageData = arrayOf(data.fullImageUrl.toString(), data.blurHash.toString())
        Navigation.findNavController(view)
            .navigate(
                SpecificCategoryFragmentDirections.actionSpecificCategoryFragmentToDownloadFragment(
                    imageData
                )
            )
    }

    private fun callBack() {
        binding.categoryName.onRightDrawableClicked {
            Navigation.findNavController(it).popBackStack()
        }

    }


    @SuppressLint("ClickableViewAccessibility")
    private fun TextView.onRightDrawableClicked(onClicked: (view: TextView) -> Unit) {
        this.setOnTouchListener { v, event ->
            var hasConsumed = false
            if (v is TextView) {
                if (event.x >= v.width - v.totalPaddingRight) {
                    if (event.action == MotionEvent.ACTION_UP) {
                        onClicked(this)
                    }
                    hasConsumed = true
                }
            }
            hasConsumed
        }
    }


}
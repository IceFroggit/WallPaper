package icefroggit.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import icefroggit.app.databinding.FragmentCategoriesBinding
import icefroggit.app.presentation.adapter.CategoryAdapterRV
import icefroggit.app.utils.ApiCategory


class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var recyclerViewAdapter: CategoryAdapterRV
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        initRecyclerViewAdapter()
        return binding.root
    }
    private fun initRecyclerViewAdapter(){
        val layoutManager = GridLayoutManager(context,3)
        recyclerViewAdapter = CategoryAdapterRV(ApiCategory.list)
        binding.categoriesRecyclerView.layoutManager = layoutManager
        binding.categoriesRecyclerView.adapter = recyclerViewAdapter

    }

}
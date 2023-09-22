package icefroggit.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import icefroggit.app.databinding.FragmentCategoriesBinding
import icefroggit.app.domain.model.Category
import icefroggit.app.presentation.adapter.CategoryAdapter
import icefroggit.app.presentation.adapter.CategoryInteractionListener
import icefroggit.app.utils.ApiCategory


class CategoriesFragment : Fragment() , CategoryInteractionListener {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var recyclerViewAdapter : CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        recyclerAdapter()

        return binding.root
    }

    private fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 2)
        recyclerViewAdapter = CategoryAdapter(ApiCategory.list,this)
        binding.categoriesRecyclerView.layoutManager = layoutManager
        binding.categoriesRecyclerView.adapter = recyclerViewAdapter
    }

    override fun onClickCategory(category: Category, view: View) {
        val action = MainFragmentDirections.actionMainFragmentToSpecificCategoryFragment(category.categoryName)
        Navigation.findNavController(view)
            .navigate(action)

    }


}
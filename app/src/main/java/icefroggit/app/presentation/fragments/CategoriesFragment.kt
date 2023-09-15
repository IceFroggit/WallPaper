package icefroggit.app.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import icefroggit.app.R
import icefroggit.app.databinding.FragmentCategoriesBinding
import icefroggit.app.presentation.adapter.RecyclerViewAdapter


class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>(
    FragmentCategoriesBinding::inflate
) {
    override var recyclerViewAdapter: RecyclerViewAdapter
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun initViewModel() {
        TODO("Not yet implemented")
    }

    override fun initRecyclerView() {
        TODO("Not yet implemented")
    }


}
package icefroggit.app.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import icefroggit.app.R
import icefroggit.app.databinding.FragmentHomeBinding
import icefroggit.app.databinding.FragmentRandomBinding
import icefroggit.app.presentation.adapter.RecyclerViewAdapter


class RandomFragment :  BaseFragment<FragmentRandomBinding>(
    FragmentRandomBinding::inflate
) {
    override fun initViewModel() {
        TODO("Not yet implemented")
    }

    override fun initRecyclerView() {
        TODO("Not yet implemented")
    }
    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter()
}
package icefroggit.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import icefroggit.app.databinding.FragmentMainBinding
import icefroggit.app.presentation.adapter.ViewPagerAdapter


class MainFragment : Fragment() {
    private val tabTitles = listOf("Home", "Popular", "Random", "Categories")
    private val fragments =
        listOf(HomeFragment(), PopularFragment(), RandomFragment(), CategoriesFragment())

    private lateinit var binding: FragmentMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        initViewPager()
        initTabLayout()
      //  initToolBar()
        return binding.root
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun initViewPager() {
        val pagerAdapter = ViewPagerAdapter(context as FragmentActivity, fragments)
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.isUserInputEnabled = false
    }

    private fun initToolBar() {
        binding.toolbar.title = "Wallapp"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

}
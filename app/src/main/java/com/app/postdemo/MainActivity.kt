package com.app.postdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.app.postdemo.fragment.PostFavorites_Fragment
import com.app.postdemo.fragment.Post_Fragment
import com.example.postdemo.R
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val tabLayout: TabLayout
        get() = findViewById(R.id.tabLayout)

    private val viewPager: ViewPager
        get() = findViewById(R.id.viewPager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = TabMenuAdapter(supportFragmentManager)

        tabLayout.removeAllTabs()
        tabLayout.addTab(tabLayout.newTab().setText("Posts"))
        tabLayout.addTab(tabLayout.newTab().setText("Favorites"))
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager)

    }


    class TabMenuAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {

            when (position) {
                0 -> return Post_Fragment()
                1 -> return PostFavorites_Fragment()
                else -> return PostFavorites_Fragment()
            }
        }
        override fun getCount(): Int {
            return 2
        }
        override fun getPageTitle(position: Int): CharSequence?     {
            when (position) {
                0 -> return "Posts"
                1 -> return "Favorites"
                else -> return null
            }
        }
    }
}
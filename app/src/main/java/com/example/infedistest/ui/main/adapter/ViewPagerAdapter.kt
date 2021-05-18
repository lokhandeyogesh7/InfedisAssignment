package com.example.infedistest.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.infedistest.ui.main.view.fragments.BookFragment
import com.example.infedistest.ui.main.view.fragments.NewsFragment

class ViewPagerAdapter(
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            NEWS_SCREEN_INDEX -> {
                NewsFragment()
            }
            BOOKS_SCREEN_INDEX -> {
                BookFragment()
            }
            else -> {
                NewsFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            NEWS_SCREEN_INDEX -> {
                NEWS_TITLE
            }
            BOOKS_SCREEN_INDEX -> {
                BOOKS_TITLE
            }
            else -> {
                NEWS_TITLE
            }
        }
    }

    companion object{
        const val  BOOKS_SCREEN_INDEX  = 1
        const val  NEWS_SCREEN_INDEX  = 0
        //declare titles
        const val BOOKS_TITLE = "Boooks"
        const val NEWS_TITLE = "News"
    }
}
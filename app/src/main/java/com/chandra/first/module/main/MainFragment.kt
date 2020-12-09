package com.chandra.first.module.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chandra.first.R
import com.chandra.first.module.home.HomeFragment
import com.chandra.first.module.profile.ProfileFragment
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNav?.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.nav_home) {
                val fragment = HomeFragment.newInstance()
                childFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, fragment.javaClass.simpleName)
                    .commit()
            } else {
                val fragment = ProfileFragment.newInstance()
                childFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, fragment.javaClass.simpleName)
                    .commit()
            }
            true
        }

        bottomNav?.selectedItemId = R.id.nav_home
    }

}
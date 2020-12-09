package com.chandra.first.module.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.chandra.first.R
import com.chandra.first.database.entity.Place
import com.chandra.first.module.main.MainFragmentDirections
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(), PlaceAdapter.Callback {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private val placeAdapter by lazy { PlaceAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPlace.adapter = placeAdapter
        viewModel.places.observe(viewLifecycleOwner, { data -> placeAdapter.setData(data) })
    }

    override fun onPlaceClick(data: Place) {
        val action = MainFragmentDirections.actionToPlace(data.id)
        val navController : NavController = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )
        navController.navigate(action)
    }

}
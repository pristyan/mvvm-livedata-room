package com.chandra.first.module.place

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.chandra.first.R
import kotlinx.android.synthetic.main.place_fragment.*

class PlaceFragment : Fragment() {

    private lateinit var viewModel: PlaceViewModel

    private val photoAdapter by lazy { PlacePhotoAdapter() }

    private val args : PlaceFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PlaceViewModel::class.java)
        return inflater.inflate(R.layout.place_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbarPlace.setNavigationOnClickListener { requireActivity().onBackPressed() }

        with(vpImage) {
            adapter = photoAdapter
            dotIndicator.setupWithViewPager(this)
        }

        viewModel.photos.observe(viewLifecycleOwner, { data ->
            photoAdapter.setPhotos(data)
        })

        viewModel.fetchPhotosById(args.placeId)
    }

}
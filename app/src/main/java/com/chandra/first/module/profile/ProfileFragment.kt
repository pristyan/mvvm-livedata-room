package com.chandra.first.module.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.chandra.first.R
import com.chandra.first.module.main.MainFragmentDirections
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        viewModel.user.observe(viewLifecycleOwner, { user ->
            Glide.with(this).load(user.photo).into(imgProfile)
            tvName?.text = user.name
            tvEmail?.text = user.email
            tvPhone?.text = user.phone
        })

        viewModel.isLoggedOut.observe(viewLifecycleOwner, {
            if (it) goToLogin()
        })

        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnLogout?.setOnClickListener { viewModel.logout() }
    }

    private fun goToLogin() {
        val action = MainFragmentDirections.actionToLogin()
        val navController : NavController = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )

        navController.navigate(action)
    }
}
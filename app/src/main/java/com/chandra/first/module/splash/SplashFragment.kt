package com.chandra.first.module.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chandra.first.R

class SplashFragment : Fragment() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        viewModel.goToNextPage.observe(viewLifecycleOwner, { destination ->
            if (destination == "login") goToLogin()
            else goToMain()
        })
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    private fun goToLogin() {
        val action = SplashFragmentDirections.actionToLogin()
        findNavController().navigate(action)
    }

    private fun goToMain() {
        val action = SplashFragmentDirections.actionToMain()
        findNavController().navigate(action)
    }
}
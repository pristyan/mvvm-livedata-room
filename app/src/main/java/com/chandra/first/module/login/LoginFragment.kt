package com.chandra.first.module.login

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chandra.first.R
import com.chandra.first.util.hideKeyboard
import com.chandra.first.util.toast
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.errorMessage.observe(viewLifecycleOwner, { msg ->
            msg?.let { toast(it) }
        })

        viewModel.loginSuccess.observe(viewLifecycleOwner, { isSuccess ->
            if (isSuccess) goToMain()
        })

        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin?.setOnClickListener {
            it.hideKeyboard(requireContext())
            viewModel.login(
                email = edtEmail.text.toString(),
                password = edtPassword.text.toString()
            )
        }
    }

    private fun goToMain() {
        val action = LoginFragmentDirections.actionToMain()
        findNavController().navigate(action)
    }

}
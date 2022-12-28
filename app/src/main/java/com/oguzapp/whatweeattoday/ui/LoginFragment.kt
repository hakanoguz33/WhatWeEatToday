package com.oguzapp.whatweeattoday.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.utils.Constants
import com.oguzapp.whatweeattoday.viewModels.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    private lateinit var loginButton: Button
    private lateinit var userNameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var forgotPasswordTextView: TextView
    private lateinit var createUserTextView: TextView
    private lateinit var changeLanguageImageView: ImageView
    private lateinit var languageTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        init(view)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.getUserList(requireContext(), view)
        viewModel.loadLanguage(requireContext(), view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        super.onViewCreated(view, savedInstanceState)
        val isConnect = viewModel.checkForInternet(requireContext())
        changeLanguageImageView.setOnClickListener {
            if (languageTextView.text == Constants.english) {
                viewModel.setLocale(Constants.localeTurkish, requireContext(), view)
                languageTextView.text = view.context.getString(R.string.text_turkish)
                activity?.recreate()
            } else {
                viewModel.setLocale(Constants.localeEnglish, requireContext(), view)
                languageTextView.text = view.context.getString(R.string.text_English)
                activity?.recreate()
            }
        }
        if (isConnect) {
            loginButton.setOnClickListener {
                val isValidate = viewModel.validateUser(
                    userNameEditText.text.toString(),
                    passwordEditText.text.toString(),
                    requireContext()
                )
                if (isValidate) {
                    Toast.makeText(
                        context,
                        view.context.getString(R.string.login_successful),
                        Toast.LENGTH_SHORT
                    ).show()
                    it.findNavController()
                        .navigate(R.id.action_loginFragment_to_selectCountryFragment)
                } else {
                    Toast.makeText(
                        context,
                        view.context.getString(R.string.username_password_failed),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    userNameEditText.error =
                        view.context.getString(R.string.username_password_failed)
                    passwordEditText.error =
                        view.context.getString(R.string.username_password_failed)
                }
            }
            forgotPasswordTextView.setOnClickListener {
                viewModel.forgotPassword()
            }
            createUserTextView.setOnClickListener {
                viewModel.createUser(view)
            }
        } else
            view.findNavController().navigate(R.id.action_loginFragment_to_errorScreenFragment)
    }

    private fun init(view: View) {
        loginButton = view.findViewById(R.id.btnLogin)
        userNameEditText = view.findViewById(R.id.editTextUserName)
        passwordEditText = view.findViewById(R.id.editTextPassword)
        forgotPasswordTextView = view.findViewById(R.id.textViewForgotPassword)
        createUserTextView = view.findViewById(R.id.textViewCreateUser)
        changeLanguageImageView = view.findViewById(R.id.changeLanguageImageView)
        languageTextView = view.findViewById(R.id.languageTextView)
    }
}
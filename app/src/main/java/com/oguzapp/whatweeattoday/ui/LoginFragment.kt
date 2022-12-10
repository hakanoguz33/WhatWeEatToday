package com.oguzapp.whatweeattoday.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.findFragment
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.viewModels.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    private lateinit var loginButton: Button
    private lateinit var userNameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var forgotPasswordTextView: TextView
    private lateinit var createUserTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        init(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginButton.setOnClickListener {
            viewModel.validateUser(
                userNameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
        forgotPasswordTextView.setOnClickListener {
            viewModel.forgotPassword()
        }
        createUserTextView.setOnClickListener {
            viewModel.createUser()
        }
    }

    private fun init(view: View) {
        loginButton = view.findViewById(R.id.btnLogin)
        userNameEditText = view.findViewById(R.id.editTextUserName)
        passwordEditText = view.findViewById(R.id.editTextPassword)
        forgotPasswordTextView = view.findViewById(R.id.textViewForgotPassword)
        createUserTextView = view.findViewById(R.id.textViewCreateUser)
    }
}
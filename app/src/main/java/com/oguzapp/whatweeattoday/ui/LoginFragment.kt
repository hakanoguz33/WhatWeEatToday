package com.oguzapp.whatweeattoday.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.db.CountryDatabase
import com.oguzapp.whatweeattoday.db.FoodTypeConverters
import com.oguzapp.whatweeattoday.db.model.CountryEntity
import com.oguzapp.whatweeattoday.network.Constants
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
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.getUserList(requireContext(),view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener {
            val isValidate = viewModel.validateUser(
                userNameEditText.text.toString(),
                passwordEditText.text.toString(),
                requireContext()
            )
            if (isValidate) {
                Toast.makeText(context, "Giriş Başarılı", Toast.LENGTH_SHORT).show()
                it.findNavController().navigate(R.id.action_loginFragment_to_selectCountryFragment)
            } else {
                Toast.makeText(context, "Kullanıcı Adı veya Şifre Hatalı", Toast.LENGTH_SHORT)
                    .show()
                userNameEditText.error = "Kullanıcı Adı veya Şifre Hatalı"
                passwordEditText.error = "Kullanıcı Adı veya Şifre Hatalı"
            }
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
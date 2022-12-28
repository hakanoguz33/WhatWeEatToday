package com.oguzapp.whatweeattoday.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.viewModels.CreateUserViewModel

class CreateUserFragment : Fragment() {

    private lateinit var viewModel: CreateUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CreateUserViewModel::class.java]
        val createUserButton = view.findViewById<Button>(R.id.createUserButton)
        createUserButton.setOnClickListener {
            viewModel.createUser(view)
        }
    }
}
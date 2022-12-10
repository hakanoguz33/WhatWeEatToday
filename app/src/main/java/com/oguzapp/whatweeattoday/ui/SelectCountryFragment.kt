package com.oguzapp.whatweeattoday.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.viewModels.SelectCountryViewModel

class SelectCountryFragment : Fragment() {

    companion object {
        fun newInstance() = SelectCountryFragment()
    }

    private lateinit var viewModel: SelectCountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_country, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SelectCountryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
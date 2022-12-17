package com.oguzapp.whatweeattoday.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.viewModels.SelectCountryViewModel

class SelectCountryFragment : Fragment() {
    private lateinit var viewModel: SelectCountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_country, container, false)
        viewModel = ViewModelProvider(this)[SelectCountryViewModel::class.java]
        viewModel.downloadCountriesList(requireContext(),view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SelectCountryViewModel::class.java]
    }
}
package com.oguzapp.whatweeattoday.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.adapters.CountriesRVAdapter
import com.oguzapp.whatweeattoday.network.Constants
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
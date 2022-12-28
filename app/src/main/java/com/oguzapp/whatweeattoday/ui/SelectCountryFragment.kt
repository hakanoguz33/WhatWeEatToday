package com.oguzapp.whatweeattoday.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
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
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    AlertDialog.Builder(context!!)
                        .setMessage(R.string.exit_app)
                        .setPositiveButton(R.string.positive_button_text) { _, _ ->
                            activity?.finish()
                        }
                        .setNegativeButton(
                            R.string.negative_button_text
                        ) { _, _ ->
                        }
                        .show()
                }
            })
        viewModel.downloadCountriesList(requireContext(), view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SelectCountryViewModel::class.java]
    }
}
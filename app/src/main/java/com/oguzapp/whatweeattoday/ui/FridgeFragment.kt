package com.oguzapp.whatweeattoday.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.viewModels.FridgeViewModel

class FridgeFragment : Fragment() {
    private lateinit var viewModel: FridgeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fridge, container, false)
        viewModel = ViewModelProvider(this)[FridgeViewModel::class.java]
        val countryName = requireArguments().getString("countryName")
        viewModel.setRecyclerView(view,countryName!!)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                viewModel.clearRecyclerView(view)
                view.findNavController().navigate(R.id.action_fridgeFragment_to_selectCountryFragment)
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
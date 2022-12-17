package com.oguzapp.whatweeattoday.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.viewModels.FridgeViewModel

class FridgeFragment : Fragment() {
    private lateinit var viewModel: FridgeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fridge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FridgeViewModel::class.java]
        val countryName = requireArguments().getString("countryName")
        viewModel.setRecyclerView(view,countryName!!)
        /*requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                view.findNavController().navigate(R.id.action_fridgeFragment_to_selectCountryFragment)
            }
        })*/
    }
}
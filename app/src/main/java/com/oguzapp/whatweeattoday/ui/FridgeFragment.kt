package com.oguzapp.whatweeattoday.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.viewModels.FridgeViewModel

class FridgeFragment : Fragment() {

    companion object {
        fun newInstance() = FridgeFragment()
    }

    private lateinit var viewModel: FridgeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fridge, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FridgeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
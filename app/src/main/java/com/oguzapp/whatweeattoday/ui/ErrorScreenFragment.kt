package com.oguzapp.whatweeattoday.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.viewModels.ErrorScreenViewModel

class ErrorScreenFragment : Fragment() {
    private lateinit var viewModel: ErrorScreenViewModel
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_error_screen, container, false)
        init(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ErrorScreenViewModel::class.java]
        swipeRefresh.setOnRefreshListener {
            viewModel.swipeRefreshSet(requireContext(),view)
        }
    }

    private fun init(view: View) {
        swipeRefresh = view.findViewById(R.id.swipeRefreshErrorScreen)
    }
}
package com.oguzapp.whatweeattoday.viewModels

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.models.User
import com.oguzapp.whatweeattoday.network.ApiClient
import com.oguzapp.whatweeattoday.utils.Constants
import com.oguzapp.whatweeattoday.network.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ErrorScreenViewModel : ViewModel() {
    private lateinit var service: RetrofitAPI
    lateinit var userList: ArrayList<User>

    fun swipeRefreshSet(context: Context, view: View) {
        service = ApiClient.getClient().create(RetrofitAPI::class.java)
        val post = service.listUser()

        post.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful)
                    userList = (response.body() as ArrayList<User>?)!!
                Constants.userList = userList
                Log.i(Constants.logUserList, Constants.logUserListDownload)
                view.findNavController().navigate(R.id.action_errorScreenFragment_to_loginFragment)
            }
        })
    }
}
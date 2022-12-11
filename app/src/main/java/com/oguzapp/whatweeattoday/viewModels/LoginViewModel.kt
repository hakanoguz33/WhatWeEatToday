package com.oguzapp.whatweeattoday.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.oguzapp.whatweeattoday.models.User
import com.oguzapp.whatweeattoday.network.ApiClient
import com.oguzapp.whatweeattoday.network.Constants
import com.oguzapp.whatweeattoday.network.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private lateinit var postService: RetrofitAPI
    lateinit var postList: ArrayList<User>
    fun validateUser(userName: String, password: String, context: Context): Boolean {
        return checkUser(Constants.userList,userName,password)
    }

    fun forgotPassword() {

    }

    fun createUser() {

    }

    fun getUserList(context: Context){
        postService = ApiClient.getClient().create(RetrofitAPI::class.java)
        val post = postService.listUser("")

        post.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful)
                    postList = (response.body() as ArrayList<User>?)!!
                Constants.userList = postList
                Log.i("userlist indi","userlistindiiiii")
            }
        })
    }

    private fun checkUser(userList: ArrayList<User>, userName: String, password: String): Boolean {
        for (user in userList)
            if (user.userName == userName && user.password == password)
                return true
        return false
    }
}
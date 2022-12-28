package com.oguzapp.whatweeattoday.viewModels

import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.models.User
import com.oguzapp.whatweeattoday.network.ApiClient
import com.oguzapp.whatweeattoday.utils.Constants
import com.oguzapp.whatweeattoday.network.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateUserViewModel : ViewModel() {

    private lateinit var retrofitAPI: RetrofitAPI

    fun createUser(view: View) {
        retrofitAPI = ApiClient.getClient().create(RetrofitAPI::class.java)
        val userName = view.findViewById<EditText>(R.id.editTextTextPersonName)
        val firstPassword = view.findViewById<EditText>(R.id.editTextTextPassword)
        val secondPassword = view.findViewById<EditText>(R.id.editTextTextPasswordAgain)
        val isPasswordValidate: Boolean =
            checkPassword(firstPassword.text.toString(), secondPassword.text.toString(), view)
        if (isPasswordValidate) {
            var user = User(
                (Constants.userList.last().userNo.toInt() + 1).toString(),
                userName.text.toString(),
                firstPassword.text.toString()
            )
            Constants.userList.add(user)
            val post = retrofitAPI.createUser(Constants.userList)
            post.enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    Toast.makeText(
                        view.context,
                        view.context.getString(R.string.user_created_successful),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Toast.makeText(view.context, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun checkPassword(password1: String, password2: String, view: View): Boolean {
        var isPasswordValidate = false;
        if (password1.isNotEmpty() && password2.isNotEmpty()) {
            if (password1 != password2)
                Toast.makeText(
                    view.context,
                    view.context.getString(R.string.password_must_be_same),
                    Toast.LENGTH_SHORT
                ).show()
            else if (password1.contains(".,"))
                Toast.makeText(
                    view.context,
                    view.context.getString(R.string.password_must_included),
                    Toast.LENGTH_SHORT
                )
                    .show()
            else if (password1 == password2 && password1.length < 7)
                Toast.makeText(
                    view.context,
                    view.context.getString(R.string.password_must_more_than),
                    Toast.LENGTH_SHORT
                )
                    .show()
            else
                isPasswordValidate = true
        } else
            Toast.makeText(
                view.context,
                view.context.getString(R.string.password_cannot_empty),
                Toast.LENGTH_SHORT
            ).show()

        return isPasswordValidate;
    }
}
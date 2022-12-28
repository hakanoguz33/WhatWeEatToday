package com.oguzapp.whatweeattoday.viewModels

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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
import java.util.*
import kotlin.collections.ArrayList

class LoginViewModel : ViewModel() {
    private lateinit var service: RetrofitAPI
    lateinit var userList: ArrayList<User>

    fun validateUser(userName: String, password: String, context: Context): Boolean {
        return checkUser(Constants.userList, userName, password)
    }

    fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    fun forgotPassword() {

    }

    fun createUser(view: View) {
        view.findNavController().navigate(R.id.action_loginFragment_to_createUserFragment)
    }

    fun getUserList(context: Context, view: View) {
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
            }
        })
    }

    private fun checkUser(userList: ArrayList<User>, userName: String, password: String): Boolean {
        for (user in userList)
            if (user.userName == userName && user.password == password)
                return true
        return false
    }

    fun setLocale(lang: String, context: Context, view: View) {
        val languageTextView = view.findViewById<TextView>(R.id.languageTextView)
        var languageImageView = view.findViewById<ImageView>(R.id.changeLanguageImageView)
        var locale: Locale
        if (lang == "tr") {
            locale = Locale("tr")
            languageImageView.setImageDrawable(context.getDrawable(R.drawable.turkey_flag))
        } else {
            locale = Locale.UK
            languageImageView.setImageDrawable(context.getDrawable(R.drawable.uk_flag))
        }

        val config = Configuration()
        config.setLocale(locale)

        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )

        val editor: SharedPreferences.Editor =
            context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", lang)
        editor.apply()

        if (lang == Constants.localeEnglish)
            languageTextView.text = Constants.english
        else
            languageTextView.text = Constants.turkish
    }

    fun loadLanguage(context: Context, view: View) {
        val pref: SharedPreferences =
            context.getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language: String = pref.getString("My_Lang", "")!!
        setLocale(language, context, view)
    }
}
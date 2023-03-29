package com.example.kotlin_android_app_assignment

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.kotlin_android_app_assignment.databinding.ActivityMainBinding
import com.example.kotlin_android_app_assignment.databinding.LoginPageBinding
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


data class User(
    var id: String,
//    var first_name: String,
//    var last_name: String,
//    var email: String,
//    var gender: String,
//    var ip_address: String
)


class LoginPage : AppCompatActivity() {
    private val client = OkHttpClient()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: LoginPageBinding
    lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener(View.OnClickListener {
            val request = Request.Builder().url("https://comp4107.herokuapp.com/user").build()

            client.newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) {
                    var userObject = JSONObject("{\'list\':"+response.body()?.string()+'}')
                    var userList = userObject.getJSONArray("list")
                    for (i in 0 until userList.length()) {
                        var user = userList[i]
                        // Your code here
                    }
                }

            })

            var username: String = binding.username.text.toString()
            var password: String = binding.password.text.toString()

        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
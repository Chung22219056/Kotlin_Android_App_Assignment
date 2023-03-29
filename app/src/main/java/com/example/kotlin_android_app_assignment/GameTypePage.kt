package com.example.kotlin_android_app_assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_android_app_assignment.databinding.ActivityMainBinding
import com.example.kotlin_android_app_assignment.databinding.CardBinding
import com.example.kotlin_android_app_assignment.databinding.FragmentFirstBinding
import com.example.kotlin_android_app_assignment.databinding.FragmentSecondBinding
import com.example.kotlin_android_app_assignment.databinding.GameTypePageBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

data class Card(
    var id: String,
    var title: String,
    var image: String,
    var quantity: String,
    var description: String,
//    var ip_address: String
)

class GameTypePage : Fragment() {
    private var _binding: GameTypePageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private var recyclerView: RecyclerView? = null
    private val client = OkHttpClient()
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = GameTypePageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view?.findViewById(R.id.recyclerView)

        var l = arrayListOf<User>()
        l.add(User("1"))
        l.add(User("2"))
        l.add(User("3"))

        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = CardAdapter(l)



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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
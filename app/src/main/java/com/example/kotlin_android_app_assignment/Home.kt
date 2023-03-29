package com.example.kotlin_android_app_assignment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.kotlin_android_app_assignment.databinding.ActivityMainBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: ActivityMainBinding
    lateinit var imageView: ImageView
    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.card, container, false)
        val infoImage: ImageView = view.findViewById(R.id.infoImage)

        val url = "https://comp4107.herokuapp.com/inventory?type=game"
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
//                println(response.body()?.string())
                var userData = JSONObject("{\'list\':" + response.body()?.string() + '}')
                var userList = userData.getJSONArray("list")
                for (i in 0 until userList.length()) {
//                    println(userList[i])
//                    var infoData = JSONObject(userList[i]?.toString())

//                    println(userData.get("_id"))
//                    println(userData.get("email"))

//                    val bMap =
//                        BitmapFactory.decodeFile("https://dummyimage.com/246x100.png/5fa2dd/ffffff")
//                    val img: InputStream = URL("https://dummyimage.com/246x100.png/5fa2dd/ffffff").openStream()
//                    val bmp = BitmapFactory.decodeStream(img)
//                    infoImage.setImageBitmap(bmp);

                }

            }

        })

        return view
    }

    fun run(url: String) {

        val request = Request.Builder()
            .url(url)
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
//                println(response.body()?.string())
                var userData = JSONObject("{\'list\':" + response.body()?.string() + '}')
                var userList = userData.getJSONArray("list")
                for (i in 0 until userList.length()) {
//                    println(userList[i])
                    var userData = JSONObject(userList[i]?.toString())
//                    var email =userList[i]?.toString()
//                    println(userData.get("_id"))
//                    println(userData.get("email"))


                }

            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }
}

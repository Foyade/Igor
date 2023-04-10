package com.example.igor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var memeList: MutableList<String>
    private lateinit var imageView: ImageView
    private lateinit var idView: TextView
    private lateinit var nameView: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.memeList)
        memeList = mutableListOf()
        getMemeImage()
        getMemeId()
        getMemeName()
    }

    private fun getMemeName() {
        val client = AsyncHttpClient()
        nameView = findViewById(R.id.name)
        client.get("https://api.imgflip.com/get_memes", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Quote", "response successful")
                val memeArray = json.jsonObject.getJSONArray("memes")

                for (i in 0 until memeArray.length()) {
                    memeList.add(memeArray.getJSONObject(i).getString("name"))
                }
                val adapter = MemeAdapter(memeList)
                recyclerView.adapter = adapter
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, throwable: Throwable?) {
                Log.d("NameError", errorResponse)
            }
        })
    }


    private fun getMemeId() {
        val client = AsyncHttpClient()
        idView = findViewById(R.id.id)
        client.get("https://api.imgflip.com/get_memes", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Quote", "response successful")
                val id = json.jsonObject.getString("id")
                idView.text = id
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, throwable: Throwable?) {
                Log.d("NameError", errorResponse)
            }
        })
    }


    private fun getMemeImage() {
        val client = AsyncHttpClient()
        imageView = findViewById(R.id.image)
        client.get("https://api.imgflip.com/get_memes", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Icon", "response successful")
                val iconUrl = json.jsonObject.getString("icon_url")
                Glide.with(this@MainActivity)
                    .load(iconUrl)
                    .fitCenter()
                    .into(imageView)
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, throwable: Throwable?) {
                Log.d("Icon Error", errorResponse)
            }
        })
    }
}

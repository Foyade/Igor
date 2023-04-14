package com.example.igor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var memeImageList: MutableList<memeItem>
    private lateinit var idList: MutableList<String>
    private lateinit var imageList: MutableList<String>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.memeRecycler)
        memeImageList = mutableListOf()
        getMemeInfo()
    }

    private fun getMemeInfo() {
        val client = AsyncHttpClient()

        client["https://api.imgflip.com/get_memes", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Quote", "response successful")
                val memeArray = json.jsonObject.getJSONObject("data").getJSONArray("memes")

                for (i in 0 until memeArray.length()) {
                    memeImageList.add(
                        memeItem(
                            memeArray.getJSONObject(i).getString("id"),
                            memeArray.getJSONObject(i).getString("name"),
                            memeArray.getJSONObject(i).getString("url")
                        )
                    )

                }
                Log.d("memeArray", "memeArray $memeImageList")
                val adapter = AdapterMeme(memeImageList)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, throwable: Throwable?) {
                Log.d("NameError", errorResponse)
            }
        }]
    }

}

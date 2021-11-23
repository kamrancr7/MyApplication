package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import data_model.Data
import data_model.DataModel

class MainActivity : AppCompatActivity() {

    private var progressBar: ProgressBar? = null
    private val dataList: MutableList<Data> = mutableListOf()
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         progressBar = findViewById<ProgressBar>(R.id.progress_Bar) as ProgressBar
        progressBar!!.visibility = View.VISIBLE

        myAdapter = MyAdapter(dataList)

        val recyclerview = findViewById<RecyclerView>(R.id.my_recycler_view)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.addItemDecoration(DividerItemDecoration(this,OrientationHelper.VERTICAL))
        recyclerview.adapter = myAdapter

        AndroidNetworking.get("https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/default/dynamodb-writer")
            .build()
            .getAsObject(DataModel::class.java,object : ParsedRequestListener<DataModel>{
                override fun onResponse(response: DataModel?) {
                    progressBar!!.visibility = View.INVISIBLE
                    if (response != null) {
                        dataList.addAll(response.results)
                    }
                    myAdapter.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {
                    TODO("Not yet implemented")
                }

            })
    }
}
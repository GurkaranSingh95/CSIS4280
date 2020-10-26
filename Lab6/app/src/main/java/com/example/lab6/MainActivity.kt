package com.example.lab6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import java.io.IOException
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private val teamListLiveData = MutableLiveData<List<Team>>()
//    val studentNames = ArrayList<String>(
//        Arrays.asList(
//            "Tushit Nag Kanuri",
//            "Nam Phan",
//            "Gukaran Singh",
//            "Agam Choudhary"
//        )
//    )
//    val studentID =
//        ArrayList<String>(Arrays.asList("300298577", "300298577", "300298577", "300298577"))

    val teamList = ArrayList<Team>()

    private val client = OkHttpClient()

    var dataService: DataService = DataService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataService.run("http://54.225.179.78:5000/data", this)

        dataService.dataListLiveData.observe(this, Observer { ms ->
            ms?.let {
                println("NEW LIST: " + it.size)
            }
        })

        recyclerView.adapter = TeamAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


    }


    }


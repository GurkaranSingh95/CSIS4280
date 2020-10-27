package com.example.lab6

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import java.io.IOException
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private val teamListLiveData = MutableLiveData<List<Team>>()

    var teamService = TeamService()
    var dataService: DataService = DataService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var context = this

        teamService.run("http://54.225.179.78:5000/team", context)


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tabLayout.selectedTabPosition) {
                    0 -> {



                        teamService.run("http://54.225.179.78:5000/team", context)


                        teamService.run("http://54.225.179.78:5000/team", context)

                    }
                    1 -> {
                        dataService.run("http://54.225.179.78:5000/data", context)
                    }


                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {


            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })


        teamService.teamListLiveData.observe(this, Observer { ms ->
            ms?.let {
                println("NEW LIST: " + it.size)
                recyclerView.adapter = TeamAdapter(it)
            }

        })

        dataService.dataListLiveData.observe(this, Observer { ms ->
            ms?.let {

                println("NEW DATA LIST: " + it.size)

                println("NEW Data LIST: " + it.size)


                recyclerView.adapter = DataAdapter(it)
            }

        })


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


    }


}


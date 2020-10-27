package com.example.lab6

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.readjsondemo.utilities.FileHelper
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view2_layout.*


class MainActivity : AppCompatActivity() {
    private val teamListLiveData = MutableLiveData<List<Team>>()

    var teamService = TeamService()
    var dataService: DataService = DataService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var context = this
        val url =
            FileHelper.getDataFromAssets(this, "config.txt")
        teamService.run(url+"team", context)


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tabLayout.selectedTabPosition) {
                    0 -> {


                        teamService.run(url+"team", context)


                    }
                    1 -> {
                        dataService.run(url+"data", context)
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


                recyclerView.adapter = DataAdapter(it,this)
            }

        })


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        addButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AddEmptyActivity::class.java)
            startActivity(intent)
        })

    }


}


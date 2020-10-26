package com.example.lab6


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val teamService = TeamService()
        var adapter = TeamAdapter(emptyList())

        teamService.run("http://54.225.179.78:5000/team", this)
        teamService.teamListLiveData.observe(this, Observer { ms ->
            ms?.let {
                println("NEW LIST: " + it.size)
                adapter.setItems(it)
            }
        })


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


    }


}